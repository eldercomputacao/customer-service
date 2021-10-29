package com.elderpereira.customerservice.controller;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.service.CustomerService;
import com.elderpereira.customerservice.service.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Tag(name = "Customer Controller")
@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerService = customerServiceImpl;
    }

    @Operation(summary = "Search for a customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "When Customer Does Not Exist in The Database", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable long id){
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Search for a customer by cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "When Customer Does Not Exist in The Database", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Customer> findByCpf(@PathVariable String cpf){
        return new ResponseEntity<>(customerService.findByCpf(cpf), HttpStatus.OK);
    }

    @Operation(summary = "Search for a customer by phone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "When Customer Does Not Exist in The Database", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Customer> findByPhone(@PathVariable String phone){
        return new ResponseEntity<>(customerService.findByPhone(phone), HttpStatus.OK);
    }

    @Operation(summary = "Search for a customer by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "When Customer Does Not Exist in The Database", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> findByEmail(@PathVariable String email){
        return new ResponseEntity<>(customerService.findByEmail(email), HttpStatus.OK);
    }

    @Operation(summary = "List of customers paginated", description = "The default size is 20, use the parameter size to change the default value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
    })
    @GetMapping
    public ResponseEntity<Page<Customer>> findAllPageable(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(customerService.findAllPageable(pageable));
    }

    @Operation(summary = "Saving a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Bad Request, problem saving invalid data", content = @Content(schema = @Schema(hidden = true)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Customer> save(@Valid @RequestBody CustomerPostRequestBody customerPostRequestBody) {
        return new ResponseEntity<>(customerService.save(customerPostRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Updating a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Bad Request, problem updated invalid data", content = @Content(schema = @Schema(hidden = true)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Customer> update(@PathVariable long id, @RequestBody CustomerPostRequestBody customerPostRequestBody) {
        return new ResponseEntity<>(customerService.update(id, customerPostRequestBody), HttpStatus.OK);
    }

    @Operation(summary = "Updating email of customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Bad Request, problem updated invalid data", content = @Content(schema = @Schema(hidden = true)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}/email/{email}")
    public ResponseEntity<Customer> updateEmail(@PathVariable long id, @PathVariable String email) {
        return new ResponseEntity<>(customerService.updateEmail(id, email), HttpStatus.OK);
    }

    @Operation(summary = "Updating phone of customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Bad Request, problem updated invalid data", content = @Content(schema = @Schema(hidden = true)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}/phone/{phone}")
    public ResponseEntity<Customer> updatePhone(@PathVariable long id, @PathVariable String phone) {
        return new ResponseEntity<>(customerService.updatePhone(id, phone), HttpStatus.OK);
    }

    @Operation(summary = "Updating cpf of customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Bad Request, problem updated invalid data", content = @Content(schema = @Schema(hidden = true)))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}/cpf/{cpf}")
    public ResponseEntity<Customer> updateCpf(@PathVariable long id, @PathVariable String cpf) {
        return new ResponseEntity<>(customerService.updateCpf(id, cpf), HttpStatus.OK);
    }

    @Operation(summary = "Remove a customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "When Customer Does Not Exist in The Database")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
