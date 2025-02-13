package com.alvaro.bankapi.infrastructure.adapters.account.controller;

import com.alvaro.bankapi.application.account.AccountServices;
import com.alvaro.bankapi.domain.account.Account;
import com.alvaro.bankapi.infrastructure.adapters.account.dto.AccountRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.account.dto.AccountSmallDto;
import com.alvaro.bankapi.infrastructure.adapters.shared.BaseResponseDto;
import com.alvaro.bankapi.infrastructure.adapters.shared.ResponseUtils;
import com.alvaro.bankapi.infrastructure.adapters.shared.ValidationsRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountServices service;


    @GetMapping("/{numberAccount}")
    public ResponseEntity<?> getAccountDetails(@PathVariable String numberAccount) {
        Account account = service.getAccountDetails(new BigDecimal(numberAccount));
        return buildResponse(account);
    }

    @GetMapping("/{customerId}/all")
    public ResponseEntity<?> getAllAccountsByCustomer(@PathVariable String customerId) {
        List<AccountSmallDto> lstAccountByCustomer = service.getAllAccountsByCustomer(Integer.parseInt(customerId));
        return buildResponse(lstAccountByCustomer);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequestDto payload, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationsRequest.validations(result);
        }
        BigDecimal newAccount = service.createAccount(payload);
        return buildResponse(newAccount);
    }


    private <T> ResponseEntity<BaseResponseDto<T>> buildResponse(T result) {
        BaseResponseDto<T> responseDto = new BaseResponseDto<>();
        responseDto.setCode(service.getCode());
        responseDto.setMessage(service.getMessage());
        responseDto.setResult(result);
        HttpStatus status = ResponseUtils.mapCodeToHttpStatus(service.getCode());
        return ResponseEntity.status(status).body(responseDto);
    }


}
