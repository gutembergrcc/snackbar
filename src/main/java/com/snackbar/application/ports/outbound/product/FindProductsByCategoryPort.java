package com.snackbar.application.ports.outbound.product;

import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.domain.product.Product;

import java.util.List;

public interface FindProductsByCategoryPort {

    List<Product> findProductsByCategory(Category category);

    List<Product> findProductsByOrderId(OrderId orderId);
}
