package com.snackbar.adapters.inbound.rest.customer;

import com.snackbar.adapters.inbound.rest.customer.mapper.CustomerMapper;
import com.snackbar.adapters.inbound.rest.customer.models.CustomerRequest;
import com.snackbar.adapters.inbound.rest.customer.models.CustomerResponse;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.ports.inbound.customer.CreateCustomerUserCasePort;
import com.snackbar.application.ports.inbound.customer.FindAllCustomersUseCasePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUserCasePort createCustomerUserCasePort;
    private final FindAllCustomersUseCasePort findAllCustomersUseCasePort;

    public CustomerController(
            final CreateCustomerUserCasePort createCustomerUserCasePort,
            final FindAllCustomersUseCasePort findAllCustomersUseCasePort) {
        this.createCustomerUserCasePort = createCustomerUserCasePort;
        this.findAllCustomersUseCasePort = findAllCustomersUseCasePort;
    }

    @Override
    public ResponseEntity<?> createCustomer(CustomerRequest request) {
        CustomerResponse output;
        try{
            String cpfSomenteDigitos = request.cpfNumber().replaceAll("\\D", "");
            var newCustomer = Customer.newCustomer(request.firstName(), request.lastName(), cpfSomenteDigitos);
            var customer = this.createCustomerUserCasePort.execute(newCustomer);
            output = CustomerMapper.toConsumerResponse(customer);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.created(URI.create("/customer" + output.id())).body(output);
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> listAllCustomers() {
        return ResponseEntity.ok().body(this.findAllCustomersUseCasePort.execute().stream().map(CustomerMapper::toConsumerResponse).toList());
    }
}
