package com.snackbar.configuration;

import com.snackbar.adapters.outbound.persistence.customer.FindCustomerAdapter;
import com.snackbar.adapters.outbound.persistence.customer.SaveCustomerAdapter;
import com.snackbar.adapters.outbound.persistence.product.DeleteProductAdapter;
import com.snackbar.adapters.outbound.persistence.product.FindProductAdapter;
import com.snackbar.adapters.outbound.persistence.product.SaveProductAdapter;
import com.snackbar.adapters.outbound.persistence.product.UpdateProductAdapter;
import com.snackbar.application.core.usecase.customer.autenticate.AutenticateCustomerByCpfUseCase;
import com.snackbar.application.core.usecase.customer.create.CreateCustomerUseCase;
import com.snackbar.application.core.usecase.customer.retrieve.FindAllCustomersUseCase;
import com.snackbar.application.core.usecase.customer.retrieve.FindCustomerByCpfUseCase;
import com.snackbar.application.core.usecase.product.create.CreateProductUseCase;
import com.snackbar.application.core.usecase.product.delete.DeleteProductUseCase;
import com.snackbar.application.core.usecase.product.retrieve.FindAllProductsUseCase;
import com.snackbar.application.core.usecase.product.retrieve.FindProductsByCategoryUseCase;
import com.snackbar.application.core.usecase.product.update.UpdateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateProductUseCase createProduct(SaveProductAdapter saveProductAdapter) {
        return new CreateProductUseCase(saveProductAdapter);
    }

    @Bean
    public DeleteProductUseCase deleteProduct(DeleteProductAdapter deleteProductAdapter) {
        return new DeleteProductUseCase(deleteProductAdapter);
    }

    @Bean
    public UpdateProductUseCase updateProduct(UpdateProductAdapter updateProductAdapter) {
        return new UpdateProductUseCase(updateProductAdapter);
    }

    @Bean
    public FindProductsByCategoryUseCase findProductsByCategory(FindProductAdapter findProductsByCategoryAdapter) {
        return new FindProductsByCategoryUseCase(findProductsByCategoryAdapter);
    }

    @Bean
    public FindAllProductsUseCase findAllProducts(FindProductAdapter findAllProductsAdapter) {
        return new FindAllProductsUseCase(findAllProductsAdapter);
    }

    @Bean
    public CreateCustomerUseCase createCustomer(SaveCustomerAdapter saveCustomerAdapter) {
        return new CreateCustomerUseCase(saveCustomerAdapter);
    }

    @Bean
    public FindAllCustomersUseCase findAllCustomers(FindCustomerAdapter findCustomerAdapter) {
        return new FindAllCustomersUseCase(findCustomerAdapter);
    }

    @Bean
    public FindCustomerByCpfUseCase findCustomerByCpfUseCase(FindCustomerAdapter findCustomerAdapter){
        return new FindCustomerByCpfUseCase(findCustomerAdapter);
    }

    @Bean
    public AutenticateCustomerByCpfUseCase autenticateCustomerByCpfUseCase(FindCustomerAdapter findCustomerAdapter){
        return new AutenticateCustomerByCpfUseCase(findCustomerAdapter);
    }
}
