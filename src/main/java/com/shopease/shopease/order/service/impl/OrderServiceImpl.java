package com.shopease.shopease.order.service.impl;

import com.shopease.shopease.common.util.StockValidator;
import com.shopease.shopease.order.exception.OrderNotFoundException;
import com.shopease.shopease.order.model.dto.OrderRequest;
import com.shopease.shopease.order.model.dto.OrderResponse;
import com.shopease.shopease.order.model.entity.OrderEntity;
import com.shopease.shopease.order.model.mapper.OrderMapper;
import com.shopease.shopease.order.orderitem.model.entity.OrderItemEntity;
import com.shopease.shopease.order.repository.OrderRepository;
import com.shopease.shopease.order.service.OrderService;
import com.shopease.shopease.product.exception.ProductNotFoundException;
import com.shopease.shopease.product.model.entity.ProductEntity;
import com.shopease.shopease.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void createOrder(OrderRequest orderRequest) {

        OrderEntity orderEntity = OrderMapper.toEntity(orderRequest);
        orderEntity.setTotalPrice(BigDecimal.ZERO);
        orderRepository.save(orderEntity);
    }

    private BigDecimal calculateTotalPrice(OrderEntity order) {

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItemEntity item : order.getItems()) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return totalPrice;
    }

    @Override
    @Transactional
    public void placeOrder(String id) {

        OrderEntity order = orderRepository.findById(id).
                orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));

        validateStock(order);
        order.setTotalPrice(calculateTotalPrice(order));

        orderRepository.save(order);

    }

    private void validateStock(OrderEntity order) {
        for (OrderItemEntity item : order.getItems()) {
            ProductEntity product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + item.getProduct().getId()));

            StockValidator.validateStock(product, item.getQuantity());

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            item.setPrice(product.getPrice());
            item.setOrder(order);
        }
    }

    @Override
    public OrderResponse getOrderById(String id) {
        OrderEntity orderEntity = orderRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));

        return OrderMapper.toResponse(orderEntity);
    }

    @Override
    public List<OrderResponse> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerIdAndIsDeletedFalse(customerId).stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteOrder(String id) {
        OrderEntity order = orderRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));

        if (order.getIsDeleted()) {
            throw new RuntimeException("Order already deleted with id: " + id);
        }
        order.setIsDeleted(true);
        orderRepository.save(order);
    }
}
