package com.snackbar.adapters.inbound.rest.product;

import com.snackbar.adapters.inbound.rest.product.mapper.ProductMapper;
import com.snackbar.adapters.inbound.rest.product.models.ProductRequest;
import com.snackbar.adapters.inbound.rest.product.models.ProductResponse;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.product.Category;
import com.snackbar.application.core.domain.product.Product;
import com.snackbar.application.core.domain.product.ProductId;
import com.snackbar.application.core.usecase.product.ProductOutput;
import com.snackbar.application.core.usecase.product.create.CreateProductCommand;
import com.snackbar.application.core.usecase.product.update.UpdateProductCommand;
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
        ProductResponse response;
        try{
            CreateProductCommand command = CreateProductCommand.with(request.name(), request.price(), request.description(), request.category());
            ProductOutput output = this.createProductUseCasePort.execute(command);
            response = ProductMapper.toProductResponse(output);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.created(URI.create("/products" + response.id())).body(response);
    }

    @Override
    public ResponseEntity<?> updateById(String id, ProductRequest request) {
        ProductResponse response;
        try{
            UpdateProductCommand command = UpdateProductCommand.with(id, request.name(), request.price(), request.description(), request.category());
            ProductOutput productOutput = this.updateProductUseCasePort.execute(command);
            response = ProductMapper.toProductResponse(productOutput);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        this.deleteProductUseCasePort.execute(id);
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
