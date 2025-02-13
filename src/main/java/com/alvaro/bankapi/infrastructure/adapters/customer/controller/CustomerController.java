package com.alvaro.bankapi.infrastructure.adapters.customer.controller;

import com.alvaro.bankapi.application.customer.CustomerService;
import com.alvaro.bankapi.domain.customer.Customer;
import com.alvaro.bankapi.infrastructure.adapters.customer.dto.CustomerRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.customer.dto.CustomerUpdateRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.shared.BaseResponseDto;
import com.alvaro.bankapi.infrastructure.adapters.shared.ResponseUtils;
import com.alvaro.bankapi.infrastructure.adapters.shared.ValidationsRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService services;

    @GetMapping("/{customerCode}")
    public ResponseEntity<BaseResponseDto<Customer>> getCustomerByCode(@PathVariable String customerCode) {
        Customer customer = services.GetCustomerByCode(Integer.parseInt(customerCode));
        return buildResponse(customer);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponseDto<Customer>> getCustomerByIdentification(@RequestParam String typeid, @RequestParam String id){
           Customer customer = services.GetCustomerByIdentification(typeid, id);
           return buildResponse(customer);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerRequestDto payload, BindingResult result){
        if (result.hasErrors()){
           return ValidationsRequest.validations(result);
        }
        int newCustomerCode = services.SaveCustomer(payload);
        return buildResponse(newCustomerCode);
    }

    @PutMapping("/{customerCode}")
    public ResponseEntity<?> updateCustomer(@PathVariable String customerCode,
                                            @Valid @RequestBody CustomerUpdateRequestDto payload,
                                            BindingResult result){
        if (result.hasErrors()){
            return ValidationsRequest.validations(result);
        }
        services.UpdateCustomer(Integer.parseInt(customerCode), payload);
        return buildResponse(null);
    }

    private <T> ResponseEntity<BaseResponseDto<T>> buildResponse(T result) {
        BaseResponseDto<T> responseDto = new BaseResponseDto<>();
        responseDto.setCode(services.getCode());
        responseDto.setMessage(services.getMessage());
        responseDto.setResult(result);
        HttpStatus status = ResponseUtils.mapCodeToHttpStatus(services.getCode());
        return ResponseEntity.status(status).body(responseDto);
    }


    //

}
