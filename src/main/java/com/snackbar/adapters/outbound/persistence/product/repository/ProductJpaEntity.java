package com.snackbar.adapters.outbound.persistence.product.repository;

import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.product.ProductId;

import javax.persistence.*;
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

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    public ProductJpaEntity() {
    }

    public ProductJpaEntity(String id, String name, BigDecimal price, String description, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public static ProductJpaEntity from(final Product product) {
        return new ProductJpaEntity(product.getId().getValue(), product.getName(), product.getPrice(), product.getDescription(), product.getCategory());
    }

    public static ProductJpaEntity from(final Category category) {
        return new ProductJpaEntity(null, null, null, null, category);
    }

    public Product toAggregate() {
        return Product.with(ProductId.from(getId()), getName(), getPrice(), getDescription(), getCategory());
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

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }
}
