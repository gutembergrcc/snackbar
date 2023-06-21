package com.snackbar.adapters.inbound.rest.customer;

import com.snackbar.adapters.inbound.rest.customer.mapper.CustomerMapper;
import com.snackbar.adapters.inbound.rest.customer.models.CustomerRequest;
import com.snackbar.adapters.inbound.rest.customer.models.CustomerResponse;
import com.snackbar.adapters.outbound.persistence.customer.repository.CustomerJpaEntity;
import com.snackbar.application.core.domain.customer.Customer;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.ports.inbound.customer.CreateCustomerUserCasePort;
import com.snackbar.application.ports.inbound.customer.FindAllCustomersUseCasePort;
import com.snackbar.application.ports.inbound.customer.FindCustomerByCpfUseCasePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController implements CustomerAPI {

    public static final int IDEMPOTENCY = 409;
    private final CreateCustomerUserCasePort createCustomerUserCasePort;
    private final FindAllCustomersUseCasePort findAllCustomersUseCasePort;
    private final FindCustomerByCpfUseCasePort findCustomerByCpfUseCasePort;

    public CustomerController(
            final CreateCustomerUserCasePort createCustomerUserCasePort,
            final FindAllCustomersUseCasePort findAllCustomersUseCasePort,
            final FindCustomerByCpfUseCasePort findCustomerByCpfUseCasePort) {
        this.createCustomerUserCasePort = createCustomerUserCasePort;
        this.findAllCustomersUseCasePort = findAllCustomersUseCasePort;
        this.findCustomerByCpfUseCasePort = findCustomerByCpfUseCasePort;
    }

    @Override
    public ResponseEntity<?> createCustomer(CustomerRequest request) {
        CustomerResponse output = null;
        try{
            final ResponseEntity<?> responseIdentifyCpf = identifyCpf(request.cpfNumber());

            if (responseIdentifyCpf.getStatusCode().is2xxSuccessful()){

                String cpfSomenteDigitos = request.cpfNumber().replaceAll("\\D", "");
                var newCustomer = Customer.newCustomer(request.firstName(), request.lastName(), cpfSomenteDigitos);
                var customer = this.createCustomerUserCasePort.execute(newCustomer);
                output = CustomerMapper.toConsumerResponse(customer);

            } else if (responseIdentifyCpf.getStatusCodeValue() == IDEMPOTENCY){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(responseIdentifyCpf.getBody());
            }
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.created(URI.create("/customer" + output.id())).body(output);
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> listAllCustomers() {
        return ResponseEntity.ok().body(this.findAllCustomersUseCasePort.execute().stream().map(CustomerMapper::toConsumerResponse).toList());
    }

    public ResponseEntity<?> identifyCpf(String cpf) {
        //TODO validar CPF
        final Optional<CustomerJpaEntity> getCpf = findCustomerByCpfUseCasePort.execute(cpf);
        final CustomerResponse customerResponse;
        try{
            if (getCpf.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        customerResponse = CustomerMapper.entityToConsumerResponse(getCpf);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(customerResponse);
    }
}
