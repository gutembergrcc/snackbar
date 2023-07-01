package com.snackbar.adapters.inbound.rest.product;

import com.snackbar.adapters.inbound.rest.product.mapper.ProductMapper;
import com.snackbar.adapters.inbound.rest.product.models.ProductRequest;
import com.snackbar.adapters.inbound.rest.product.models.ProductResponse;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.product.ProductId;
import com.snackbar.application.ports.inbound.product.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController implements ProductAPI{

    private final CreateProductUseCasePort createProductUseCasePort;
    private final UpdateProductUseCasePort updateProductUseCasePort;
    private final DeleteProductUseCasePort deleteProductUseCasePort;
    private final FindProductsByCategoryUseCasePort findProductByCategoryPort;
    private final FindAllProductsUseCasePort findAllProductsUseCasePort;

    public ProductController(
            final CreateProductUseCasePort createProductUseCasePort,
            final UpdateProductUseCasePort updateProductUseCasePort,
            final DeleteProductUseCasePort deleteProductUseCasePort,
            final FindProductsByCategoryUseCasePort findProductByCategoryPort,
            final FindAllProductsUseCasePort findAllProductsUseCasePort) {
        this.createProductUseCasePort = createProductUseCasePort;
        this.updateProductUseCasePort = updateProductUseCasePort;
        this.deleteProductUseCasePort = deleteProductUseCasePort;
        this.findProductByCategoryPort = findProductByCategoryPort;
        this.findAllProductsUseCasePort = findAllProductsUseCasePort;
    }

    @Override
    public ResponseEntity<?> createProduct(ProductRequest request) {
        ProductResponse output;
        try{
            var newProduct = Product.newProduct(request.name(), request.price(), request.description(), request.category());
            var product = this.createProductUseCasePort.execute(newProduct);
            output = ProductMapper.toProductResponse(product);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.created(URI.create("/products" + output.id())).body(output);
    }

    @Override
    public ResponseEntity<?> updateById(String id, ProductRequest request) {
        ProductResponse output;
        try{
            var productId = ProductId.from(id);
            var updateProduct = Product.with(productId, request.name(), request.price(), request.description(), request.category());
            var product = this.updateProductUseCasePort.execute(updateProduct);
            output = ProductMapper.toProductResponse(product);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.ok().body(output);
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        this.deleteProductUseCasePort.execute(ProductId.from(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProductResponse>> listProductByCategory(Category category) {
        return ResponseEntity.ok().body(this.findProductByCategoryPort.execute(category).stream().map(ProductMapper::toProductResponse).toList());
    }

    @Override
    public ResponseEntity<List<ProductResponse>> listAllProducts() {
        return ResponseEntity.ok().body(this.findAllProductsUseCasePort.execute().stream().map(ProductMapper::toProductResponse).toList());
    }
}
