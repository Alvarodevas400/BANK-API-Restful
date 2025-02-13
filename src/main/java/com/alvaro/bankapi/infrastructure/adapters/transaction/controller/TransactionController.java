package com.alvaro.bankapi.infrastructure.adapters.transaction.controller;

import com.alvaro.bankapi.application.transaction.TransactionService;
import com.alvaro.bankapi.domain.transaction.Transaction;
import com.alvaro.bankapi.infrastructure.adapters.shared.BaseResponseDto;
import com.alvaro.bankapi.infrastructure.adapters.shared.ResponseUtils;
import com.alvaro.bankapi.infrastructure.adapters.shared.ValidationsRequest;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionRequestCreateDto;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;


    @GetMapping
    public ResponseEntity<?> getTransactions(@Valid @ModelAttribute TransactionRequestDto queryParameters, BindingResult result) {
        if (result.hasErrors()){
            return ValidationsRequest.validations(result);
        }
        List<Transaction> lst_transaction;
        lst_transaction = service.getTransactions(queryParameters);
        return buildResponse(lst_transaction);
    }

    @PostMapping
    public ResponseEntity<?> createTransactions(@Valid @RequestBody TransactionRequestCreateDto payload , BindingResult result){
        if (result.hasErrors()){
            return ValidationsRequest.validations(result);
        }
        List<TransactionResponseDto> lst_errorTransaction;
        lst_errorTransaction = service.createTransaction(payload);
        return buildResponse(lst_errorTransaction);
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
