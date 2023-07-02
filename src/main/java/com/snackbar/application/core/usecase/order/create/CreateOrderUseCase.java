package com.snackbar.application.core.usecase.order.create;

import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.customer.CustomerId;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.exceptions.ErrorName;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.OrderItem;
import com.snackbar.application.core.domain.product.ProductId;
import com.snackbar.application.core.domain.validation.Error;
import com.snackbar.application.core.domain.validation.Notification;
import com.snackbar.application.core.usecase.order.OrderOutput;
import com.snackbar.application.ports.inbound.order.CreateOrderUseCasePort;
import com.snackbar.application.ports.outbound.customer.FindCustomerByIdPort;
import com.snackbar.application.ports.outbound.order.SaveOrderPort;
import com.snackbar.application.ports.outbound.product.FindProductByIdPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateOrderUseCase implements CreateOrderUseCasePort {

    private final SaveOrderPort saveOrderPort;

    private final FindCustomerByIdPort findCustomerByIdPort;

    private final FindProductByIdPort findProductByIdPort;

    public CreateOrderUseCase(SaveOrderPort saveOrderPort,
                              FindCustomerByIdPort findCustomerByIdPort,
                              FindProductByIdPort findProductByIdPort) {
        this.saveOrderPort = saveOrderPort;
        this.findCustomerByIdPort = findCustomerByIdPort;
        this.findProductByIdPort = findProductByIdPort;
    }

    @Override
    public OrderOutput execute(CreateOrderCommand command) {
        Optional<Customer> customer = getCustomer(command);
        var order = Order.newOrder(command.ticket(), getOrderItemsValid(command), customer.get(), command.observation());

        final var notification = Notification.create();
        order.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return OrderOutput.from(this.saveOrderPort.save(order));
    }

    private Optional<Customer> getCustomer(CreateOrderCommand command) {
        final var customerId = command.customerId();
        var customer = this.findCustomerByIdPort.findCustomerById(CustomerId.from(customerId));
        if(customer.isEmpty()){
            throw DomainException.with(new Error(ErrorName.CUSTOMER_NOT_EXIST));
        }
        return customer;
    }

    private List<OrderItem> getOrderItemsValid(CreateOrderCommand command) {
        List<OrderItem> orderItems = new ArrayList<>();
        List<CreateOrderItemCommand> itemsCommand = command.items();
        for (CreateOrderItemCommand itemCommand : itemsCommand) {
            var product = this.findProductByIdPort.findProductById(ProductId.from(itemCommand.productId()));
            if(product.isEmpty()){
                throw DomainException.with(new Error(ErrorName.PRODUCT_NOT_EXIST));
            }

            orderItems.add(OrderItem.newOrderItem(product.get(), itemCommand.quantity()));
        }

        return orderItems;
    }
}
