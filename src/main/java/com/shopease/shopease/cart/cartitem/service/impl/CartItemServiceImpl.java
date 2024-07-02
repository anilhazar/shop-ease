package com.shopease.shopease.cart.cartitem.service.impl;

import com.shopease.shopease.cart.cartitem.exception.CartItemNotFoundException;
import com.shopease.shopease.cart.cartitem.exception.InvalidCartItemException;
import com.shopease.shopease.cart.cartitem.model.dto.CartItemChangeQuantityRequest;
import com.shopease.shopease.cart.cartitem.model.dto.CartItemRequest;
import com.shopease.shopease.cart.cartitem.model.dto.CartItemResponse;
import com.shopease.shopease.cart.cartitem.model.entity.CartItemEntity;
import com.shopease.shopease.cart.cartitem.model.mapper.CartItemMapper;
import com.shopease.shopease.cart.cartitem.repository.CartItemRepository;
import com.shopease.shopease.cart.cartitem.service.CartItemService;
import com.shopease.shopease.cart.model.entity.CartEntity;
import com.shopease.shopease.cart.repository.CartRepository;
import com.shopease.shopease.common.util.StockValidator;
import com.shopease.shopease.product.model.entity.ProductEntity;
import com.shopease.shopease.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addCartItem(CartItemRequest cartItemRequest) {
        Optional<CartItemEntity> existingCartItem = cartItemRepository.
                findByCartIdAndProductIdAndIsDeletedFalse(cartItemRequest.getCartId(), cartItemRequest.getProductId());

        if (existingCartItem.isPresent()) {
            CartItemEntity cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());
            cartItemRepository.save(cartItem);
            return;
        }
        CartItemEntity cartItemEntity = CartItemMapper.toEntity(cartItemRequest);
        cartItemRepository.save(cartItemEntity);

    }

    @Override
    public CartItemResponse getCartItemById(String id) {
        return cartItemRepository.findById(id)
                .map(CartItemMapper::toResponse)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id " + id));
    }

    @Override
    @Transactional
    public void updateCartItemQuantity(String itemId, CartItemChangeQuantityRequest changeQuantityRequest) {
        Long quantity = changeQuantityRequest.getQuantity();
        CartItemEntity cartItem = cartItemRepository.findByIdAndIsDeletedFalse(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found with id: " + itemId));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItemResponse> getCartItemsByCartId(String cartId) {
        List<CartItemEntity> cartItems = cartItemRepository.findAllByCartIdAndIsDeletedFalse(cartId);
        return cartItems.stream()
                .map(CartItemMapper::toResponse)
                .toList();
    }

    @Override
    public void removeCartItem(String id) {
        CartItemEntity cartItem = cartItemRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id " + id));

        if (cartItem.getIsDeleted()) {
            throw new RuntimeException("Cart item already deleted with id: " + id);
        }
        cartItem.setIsDeleted(true);
        cartItemRepository.save(cartItem);

        CartEntity cart = cartItem.getCart();
        cart.getItems().removeIf(item -> item.getId().equals(id));
        cart.setTotalPrice(calculateTotalPrice(cart));
        cartRepository.save(cart);
    }

    private ProductEntity validateProductAndStock(CartItemRequest cartItemRequest) {
        ProductEntity product = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(() -> new InvalidCartItemException("Product not found with id: " + cartItemRequest.getProductId()));

        StockValidator.validateStock(product, cartItemRequest.getQuantity());

        return product;
    }

    private CartItemEntity buildCartItem(CartItemRequest cartItemRequest, ProductEntity product, CartEntity cart) {
        return CartItemEntity.builder()
                .cart(cart)
                .product(product)
                .quantity(cartItemRequest.getQuantity())
                .price(product.getPrice())
                .build();
    }

    private void updateProductStock(ProductEntity product, Long quantity) {
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    private BigDecimal calculateTotalPrice(CartEntity cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItemEntity item : cart.getItems()) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return totalPrice;
    }
}
