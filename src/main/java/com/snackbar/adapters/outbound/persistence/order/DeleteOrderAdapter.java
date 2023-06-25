package com.snackbar.adapters.outbound.persistence.order;

import com.snackbar.adapters.outbound.persistence.order.repository.OrderRepository;
import com.snackbar.application.core.domain.order.OrderId;
import com.snackbar.application.ports.outbound.order.DeleteOrderPort;
import org.springframework.stereotype.Component;

@Component
public class DeleteOrderAdapter implements DeleteOrderPort {

    private final OrderRepository repository;

    public DeleteOrderAdapter(OrderRepository repository) {
        this.repository = repository;
    }


    @Override
    public void deleteById(OrderId orderId) {
        if (orderId != null && this.repository.existsById(orderId.getValue())) {
            this.repository.deleteById(orderId.getValue());
        }
    }
}
