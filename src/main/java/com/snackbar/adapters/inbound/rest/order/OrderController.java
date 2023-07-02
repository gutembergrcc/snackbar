package com.snackbar.adapters.inbound.rest.order;

import com.snackbar.adapters.inbound.rest.order.mapper.OrderItemMapper;
import com.snackbar.adapters.inbound.rest.order.mapper.OrderMapper;
import com.snackbar.adapters.inbound.rest.order.models.CreateOrderRequest;
import com.snackbar.adapters.inbound.rest.order.models.OrderResponse;
import com.snackbar.adapters.inbound.rest.order.models.UpdateStatusRequest;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.domain.order.OrderStatus;
import com.snackbar.application.core.usecase.order.create.CreateOrderCommand;
import com.snackbar.application.core.usecase.order.update.UpdateOrderStatusCommand;
import com.snackbar.application.core.usecase.order.update.UpdateOrderStatusUseCase;
import com.snackbar.application.ports.inbound.order.CreateOrderUseCasePort;
import com.snackbar.application.ports.inbound.order.FindAllOrdersUseCasePort;
import com.snackbar.application.ports.inbound.order.FindOrdersByStatusUseCasePort;
import com.snackbar.application.ports.inbound.order.UpdateOrderStatusUseCasePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController implements OrderAPI {

    private final CreateOrderUseCasePort createOrderUseCasePort;
    private final FindAllOrdersUseCasePort findAllOrdersUseCasePort;
    private final FindOrdersByStatusUseCasePort findOrdersByStatusUseCasePort;
    private final UpdateOrderStatusUseCasePort updateOrderStatusUseCasePort;

    public OrderController(CreateOrderUseCasePort createOrderUseCasePort,
                           FindAllOrdersUseCasePort findAllOrdersUseCasePort,
                           FindOrdersByStatusUseCasePort findOrdersByStatusUseCasePort,
                           UpdateOrderStatusUseCasePort updateOrderStatusUseCasePort) {
        this.createOrderUseCasePort = createOrderUseCasePort;
        this.findAllOrdersUseCasePort = findAllOrdersUseCasePort;
        this.findOrdersByStatusUseCasePort = findOrdersByStatusUseCasePort;
        this.updateOrderStatusUseCasePort = updateOrderStatusUseCasePort;
    }

    @Override
    public ResponseEntity<?> createOrder(CreateOrderRequest request) {
        OrderResponse response;
        try{
            var items = OrderItemMapper.toOrderItemCommand(request.items());
            var command = CreateOrderCommand.with(request.ticket(), items, request.customerId(), request.observation());
            var order = this.createOrderUseCasePort.execute(command);
            response = OrderMapper.toOrderResponse(order);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.created(URI.create("/orders" + response.id())).body(response);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listAllOrders() {
        return ResponseEntity.ok().body(this.findAllOrdersUseCasePort.execute().stream().map(OrderMapper::toOrderResponse).toList());
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listOrdersByStatus(OrderStatus status) {
        return ResponseEntity.ok().body(this.findOrdersByStatusUseCasePort.execute(status).stream().map(OrderMapper::toOrderResponse).toList());
    }

    @Override
    public ResponseEntity<?> updateStatusById(String id, UpdateStatusRequest request) {
        OrderResponse output;
        try{
            var command = UpdateOrderStatusCommand.with(id, request.status());
            var order = this.updateOrderStatusUseCasePort.execute(command);
            output = OrderMapper.toOrderResponse(order);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.ok().body(output);
    }
}
