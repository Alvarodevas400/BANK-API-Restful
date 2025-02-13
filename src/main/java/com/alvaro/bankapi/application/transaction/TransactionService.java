package com.alvaro.bankapi.application.transaction;

import com.alvaro.bankapi.SRVTRN000.DS_INPLSTTRN;
import com.alvaro.bankapi.SRVTRN100.DS_INPREGTRN;
import com.alvaro.bankapi.SRVTRN100.DS_RESREGTRN;
import com.alvaro.bankapi.domain.transaction.Transaction;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionRequestCreateDto;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionResponseDto;
import com.alvaro.bankapi.infrastructure.adapters.transaction.mappers.TransactionMapper;
import com.alvaro.bankapi.infrastructure.adapters.transaction.repository.TransactionRepositoryAS400;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private int code;
    private String message;

    @Autowired
    private TransactionRepositoryAS400 transactionRepository;

    public List<Transaction> getTransactions(TransactionRequestDto payload){
        DS_INPLSTTRN filters = TransactionServicesMapper.toFilterAS400(payload);
        List<Transaction> transactionsResponse;
        transactionsResponse = transactionRepository.getTransactions(filters);
        code = transactionRepository.getCode();
        message =transactionRepository.getMessage();
        return transactionsResponse;
    }

    public List<TransactionResponseDto> createTransaction(TransactionRequestCreateDto payload){
        DS_INPREGTRN inputTransaction = TransactionServicesMapper.toTransactionAS400(payload);
        List<DS_RESREGTRN> outputTransaction;
        outputTransaction = transactionRepository.createTransaction(inputTransaction);
        code = transactionRepository.getCode();
        message =transactionRepository.getMessage();
        return TransactionServicesMapper.fromTransactionRepository(outputTransaction);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
