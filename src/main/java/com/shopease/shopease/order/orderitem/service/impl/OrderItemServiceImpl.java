package com.shopease.shopease.order.orderitem.service.impl;

import com.shopease.shopease.common.exception.ResourceNotFoundException;
import com.shopease.shopease.order.model.entity.OrderEntity;
import com.shopease.shopease.order.orderitem.exception.InvalidOrderItemException;
import com.shopease.shopease.order.orderitem.exception.OrderItemNotFoundException;
import com.shopease.shopease.order.orderitem.model.dto.OrderItemRequest;
import com.shopease.shopease.order.orderitem.model.dto.OrderItemResponse;
import com.shopease.shopease.order.orderitem.model.entity.OrderItemEntity;
import com.shopease.shopease.order.orderitem.model.mapper.OrderItemMapper;
import com.shopease.shopease.order.orderitem.repository.OrderItemRepository;
import com.shopease.shopease.order.orderitem.service.OrderItemService;
import com.shopease.shopease.order.repository.OrderRepository;
import com.shopease.shopease.product.model.entity.ProductEntity;
import com.shopease.shopease.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ProductRepository productRepository,
                                OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void createOrderItem(OrderItemRequest orderItemRequest) {
        ProductEntity product = validateProductAndStock(orderItemRequest);

        OrderEntity order = orderRepository.findByIdAndIsDeletedFalse(orderItemRequest.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderItemRequest.getOrderId()));

        Optional<OrderItemEntity> existingOrderItemOpt = orderItemRepository
                .findByOrderIdAndProductIdAndIsDeletedFalse(order.getId(), product.getId());

        if (existingOrderItemOpt.isPresent()) {
            OrderItemEntity existingOrderItem = existingOrderItemOpt.get();
            existingOrderItem.setQuantity(existingOrderItem.getQuantity() + orderItemRequest.getQuantity());
            existingOrderItem.setPrice(existingOrderItem.getPrice().add(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()))));
            orderItemRepository.save(existingOrderItem);
            return;
        }
        OrderItemEntity orderItem = buildOrderItem(orderItemRequest, product, order);
        orderItemRepository.save(orderItem);


        updateProductStock(product, orderItemRequest.getQuantity());
    }

    private ProductEntity validateProductAndStock(OrderItemRequest orderItemRequest) {
        ProductEntity product = productRepository.findById(orderItemRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderItemRequest.getProductId()));

        if (product.getStock() < orderItemRequest.getQuantity()) {
            throw new InvalidOrderItemException("Not enough stock for product: " + product.getName());
        }

        return product;
    }

    private OrderItemEntity buildOrderItem(OrderItemRequest orderItemRequest, ProductEntity product, OrderEntity order) {

        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()));

        return OrderItemEntity.builder()
                .order(order)
                .product(product)
                .quantity(orderItemRequest.getQuantity())
                .price(totalPrice)
                .build();
    }

    private void updateProductStock(ProductEntity product, Long quantity) {

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

    }

    @Override
    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemRepository.findAllByIsDeletedFalse().stream()
                .map(OrderItemMapper::toResponse)
                .toList();
    }

    @Override
    public List<OrderItemResponse> getOrderItemsByOrder(String orderId) {
        return orderItemRepository.findByOrderIdAndIsDeletedFalse(orderId).stream()
                .map(OrderItemMapper::toResponse)
                .toList();
    }


    @Override
    public OrderItemResponse getOrderItemById(String id) {

        return orderItemRepository.findByOrderIdAndIsDeletedFalse(id)
                .map(OrderItemMapper::toResponse)
                .orElseThrow(() -> new OrderItemNotFoundException("Order item not found with id " + id));

    }

    @Override
    public void deleteOrderItem(String id) {

        OrderItemEntity orderItem = orderItemRepository.findByOrderIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new OrderItemNotFoundException("Order item not found with id " + id));

        if (orderItem.getIsDeleted()) {
            throw new InvalidOrderItemException("Order item already deleted with id: " + id);
        }
        orderItem.setIsDeleted(true);
        orderItemRepository.save(orderItem);

    }

}
