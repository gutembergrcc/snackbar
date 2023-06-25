package com.snackbar.adapters.inbound.rest.order;

import com.snackbar.adapters.inbound.rest.order.mapper.OrderMapper;
import com.snackbar.adapters.inbound.rest.order.models.OrderRequest;
import com.snackbar.adapters.inbound.rest.order.models.OrderResponse;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.order.Order;
import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.core.domain.order.Status;
import com.snackbar.application.ports.inbound.order.CreateOrderUseCasePort;
import com.snackbar.application.ports.inbound.order.DeleteOrderUseCasePort;
import com.snackbar.application.ports.inbound.order.FindAllOrdersUseCasePort;
import com.snackbar.application.ports.inbound.order.FindOrdersByStatusUseCasePort;
import com.snackbar.application.ports.inbound.order.UpdateOrderUseCasePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController implements OrderAPI {

    private final CreateOrderUseCasePort createOrderUseCasePort;
    private final UpdateOrderUseCasePort updateOrderUseCasePort;
    private final DeleteOrderUseCasePort deleteOrderUseCasePort;
    private final FindOrdersByStatusUseCasePort findOrdersByStatusUseCasePort;
    private final FindAllOrdersUseCasePort findAllOrdersUseCasePort;

    public OrderController(
            final CreateOrderUseCasePort createOrderUseCasePort,
            final UpdateOrderUseCasePort updateOrderUseCasePort,
            final DeleteOrderUseCasePort deleteOrderUseCasePort,
            final FindOrdersByStatusUseCasePort findOrdersByStatusUseCasePort,
            final FindAllOrdersUseCasePort findAllOrdersUseCasePort) {
        this.createOrderUseCasePort = createOrderUseCasePort;
        this.updateOrderUseCasePort = updateOrderUseCasePort;
        this.deleteOrderUseCasePort = deleteOrderUseCasePort;
        this.findOrdersByStatusUseCasePort = findOrdersByStatusUseCasePort;
        this.findAllOrdersUseCasePort = findAllOrdersUseCasePort;
    }

    @Override
    public ResponseEntity<?> createOrder(OrderRequest request) {
        OrderResponse output;
        try {
            var newOrder = Order.newOrder(request.productId(), request.customerId(), request.description(), request.status());
            var order = this.createOrderUseCasePort.execute(newOrder);
            output = OrderMapper.toOrderResponse(order);
        } catch (DomainException e) {
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.created(URI.create("/orders" + output.id())).body(output);
    }

    @Override
    public ResponseEntity<?> updateById(String id, OrderRequest request) {
        OrderResponse output;
        try {
            var orderId = OrderId.from(id);
            var updateOrder = Order.with(orderId, request.productId(), request.customerId(), request.description(), request.status());
            var order = this.updateOrderUseCasePort.execute(updateOrder);
            output = OrderMapper.toOrderResponse(order);
        } catch (DomainException e) {
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.ok().body(output);
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        this.deleteOrderUseCasePort.execute(OrderId.from(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listOrderByStatus(Status status) {
        return ResponseEntity.ok().body(this.findOrdersByStatusUseCasePort.execute(status).stream().map(OrderMapper::toOrderResponse).toList());
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listAllOrders() {
        return ResponseEntity.ok().body(this.findAllOrdersUseCasePort.execute().stream().map(OrderMapper::toOrderResponse).toList());
    }

}
