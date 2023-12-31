package com.snackbar.adapters.inbound.rest.customer;

import com.snackbar.adapters.inbound.rest.customer.mapper.CustomerMapper;
import com.snackbar.adapters.inbound.rest.customer.models.AutenticateRequest;
import com.snackbar.adapters.inbound.rest.customer.models.CustomerRequest;
import com.snackbar.adapters.inbound.rest.customer.models.CustomerResponse;
import com.snackbar.application.core.domain.exceptions.DomainException;
import com.snackbar.application.core.usecase.customer.create.CreateCustomerCommand;
import com.snackbar.application.core.usecase.customer.CustomerOutput;
import com.snackbar.application.ports.inbound.customer.AutenticateCustomerByCpfUseCasePort;
import com.snackbar.application.ports.inbound.customer.CreateCustomerUseCasePort;
import com.snackbar.application.ports.inbound.customer.FindAllCustomersUseCasePort;
import com.snackbar.application.ports.inbound.customer.FindCustomerByCpfUseCasePort;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCasePort createCustomerUserCasePort;
    private final FindAllCustomersUseCasePort findAllCustomersUseCasePort;
    private final FindCustomerByCpfUseCasePort findCustomerByCpfUseCasePort;
    private final AutenticateCustomerByCpfUseCasePort autenticateCustomerByCpfUseCasePort;

    public CustomerController(
            final CreateCustomerUseCasePort createCustomerUserCasePort,
            final FindAllCustomersUseCasePort findAllCustomersUseCasePort,
            final FindCustomerByCpfUseCasePort findCustomerByCpfUseCasePort,
            AutenticateCustomerByCpfUseCasePort autenticateCustomerByCpfUseCasePort) {
        this.createCustomerUserCasePort = createCustomerUserCasePort;
        this.findAllCustomersUseCasePort = findAllCustomersUseCasePort;
        this.findCustomerByCpfUseCasePort = findCustomerByCpfUseCasePort;
        this.autenticateCustomerByCpfUseCasePort = autenticateCustomerByCpfUseCasePort;
    }

    @Override
    public ResponseEntity<?> createCustomer(CustomerRequest request) {
        final CustomerOutput customerInBase = this.findCustomerByCpfUseCasePort.execute(request.cpfNumber());
        try{
            if (customerInBase == null){
                var command = CreateCustomerCommand.with(request.firstName(), request.lastName(), request.cpfNumber());
                var customer = this.createCustomerUserCasePort.execute(command);
                CustomerResponse output = CustomerMapper.toConsumerResponse(customer);
                return ResponseEntity.created(URI.create("/customer" + output.id())).body(output);

            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(customerInBase);
            }
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> listAllCustomers() {
        return ResponseEntity.ok().body(this.findAllCustomersUseCasePort.execute().stream().map(CustomerMapper::toConsumerResponse).toList());
    }

    @Override
    public ResponseEntity<?> autenticateCustomer(@Valid AutenticateRequest request) {
        try{
            final CustomerOutput customer = this.autenticateCustomerByCpfUseCasePort.execute(request.getCpfNumber());
            return ResponseEntity.ok().body(CustomerMapper.toConsumerResponse(customer));
        }catch (DomainException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getErrors());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String mensagemErro = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest().body(mensagemErro);
    }
}
