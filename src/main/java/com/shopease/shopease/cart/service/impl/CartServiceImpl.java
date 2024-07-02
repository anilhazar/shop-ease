package com.shopease.shopease.cart.service.impl;

import com.shopease.shopease.cart.exception.CartNotFoundException;
import com.shopease.shopease.cart.model.dto.CartRequest;
import com.shopease.shopease.cart.model.dto.CartResponse;
import com.shopease.shopease.cart.model.entity.CartEntity;
import com.shopease.shopease.cart.model.mapper.CartMapper;
import com.shopease.shopease.cart.repository.CartRepository;
import com.shopease.shopease.cart.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void createCart(CartRequest cartRequest) {
        CartEntity cart = CartMapper.toEntity(cartRequest);
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);

    }

    @Override
    public CartResponse getCartByCustomerId(String customerId) {
        return cartRepository.findByCustomerIdAndIsDeletedFalse(customerId)
                .map(CartMapper::toResponse)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for customer id " + customerId));
    }

    @Override
    @Transactional
    public CartResponse emptyCart(String cartId) {
        CartEntity cart = cartRepository.findByIdAndIsDeletedFalse(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
        cart.getItems().clear();
        CartEntity cartEntity = cartRepository.save(cart);
        return CartMapper.toResponse(cartEntity);
    }

    @Override
    public void deleteCart(String id) {
        CartEntity cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id " + id));

        if (cart.getIsDeleted()) {
            throw new RuntimeException("Cart already deleted with id: " + id);
        }

        cart.setIsDeleted(true);
        cartRepository.save(cart);
    }

}
