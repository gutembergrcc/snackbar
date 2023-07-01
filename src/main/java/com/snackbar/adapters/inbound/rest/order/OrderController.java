package com.snackbar.adapters.inbound.rest.order;

import com.snackbar.adapters.inbound.rest.order.mapper.OrderItemMapper;
import com.snackbar.adapters.inbound.rest.order.mapper.OrderMapper;
import com.snackbar.adapters.inbound.rest.order.models.CreateOrderRequest;
import com.snackbar.adapters.inbound.rest.order.models.OrderResponse;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.usecase.order.create.CreateOrderCommand;
import com.snackbar.application.ports.inbound.order.CreateOrderUseCasePort;
import com.snackbar.application.ports.inbound.order.FindAllOrdersUseCasePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController implements OrderAPI {

    private final CreateOrderUseCasePort createOrderUseCasePort;
    private final FindAllOrdersUseCasePort findAllOrdersUseCasePort;

    public OrderController(CreateOrderUseCasePort createOrderUseCasePort,
                           FindAllOrdersUseCasePort findAllOrdersUseCasePort) {
        this.createOrderUseCasePort = createOrderUseCasePort;
        this.findAllOrdersUseCasePort = findAllOrdersUseCasePort;
    }

    @Override
    public ResponseEntity<?> createOrder(CreateOrderRequest request) {
        OrderResponse output;
        try{
            var items = OrderItemMapper.toOrderItem(request.items());
            var command = CreateOrderCommand.with(request.ticket(), items, request.customerId(), request.observation());
            var order = this.createOrderUseCasePort.execute(command);
            output = OrderMapper.toOrderResponse(order);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.created(URI.create("/orders" + output.id())).body(output);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listAllOrders() {
        return ResponseEntity.ok().body(this.findAllOrdersUseCasePort.execute().stream().map(OrderMapper::toOrderResponse).toList());
    }
}
