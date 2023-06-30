package com.snackbar.adapters.outbound.persistence.product.repository;

import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.product.ProductId;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity(name = "Product")
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;



    public ProductJpaEntity() {
    }

    public ProductJpaEntity(String id, String name, BigDecimal price,Integer quantity, String description, Category category, OrderId orderId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
    }

    public static ProductJpaEntity from(final Product product) {
        return new ProductJpaEntity(product.getId().getValue(), product.getName(), product.getPrice(), product.getQuantity(), product.getDescription(), product.getCategory(), null);
    }

    public static ProductJpaEntity from(final Category category) {
        return new ProductJpaEntity(null, null, null, null, null, category, null);
    }

    public static ProductJpaEntity from(final OrderId orderId) {
        return new ProductJpaEntity(null, null, null, null, null, null, orderId);
    }

    public Product toAggregate() {
        return Product.with(ProductId.from(getId()), getName(), getPrice(), getQuantity(), getDescription(), getCategory());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity(){return quantity;}

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }
}
