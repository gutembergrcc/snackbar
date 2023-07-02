package com.snackbar.adapters.outbound.persistence.order.repository;

import com.snackbar.adapters.outbound.persistence.product.repository.ProductJpaEntity;
import com.snackbar.application.core.domain.order.OrderItem;
import com.snackbar.application.core.domain.order.OrderItemId;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.product.ProductId;

import javax.persistence.*;

@Entity(name = "OrderItem")
@Table(name = "order_items")
public class OrderItemJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderJpaEntity orderJpaEntity;

    public OrderItemJpaEntity() {
    }

    public OrderItemJpaEntity(String id, ProductJpaEntity product, Integer quantity, OrderJpaEntity orderJpaEntity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.orderJpaEntity = orderJpaEntity;
    }

    public OrderItemJpaEntity(String id, ProductJpaEntity product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderItemJpaEntity from(final OrderJpaEntity orderJpaEntity, final OrderItem orderItem) {
        var productJpaEntity = ProductJpaEntity.from(orderItem.getProduct());
        return new OrderItemJpaEntity(
                orderItem.getId().getValue(),
                productJpaEntity,
                orderItem.getQuantity(),
                orderJpaEntity);
    }

    public OrderItem toAggregate() {
        var product = Product.with(ProductId.from(getProduct().getId()), getProduct().getName(), getProduct().getPrice(), getProduct().getDescription(), getProduct().getCategory());
        return OrderItem.with(OrderItemId.from(getId()), product, getQuantity());
    }

    public String getId() {
        return id;
    }

    public ProductJpaEntity getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderJpaEntity getOrderJpaEntity() {
        return orderJpaEntity;
    }
}
