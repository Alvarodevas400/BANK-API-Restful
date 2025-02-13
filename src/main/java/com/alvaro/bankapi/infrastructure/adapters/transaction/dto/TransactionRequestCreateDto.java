package com.alvaro.bankapi.infrastructure.adapters.transaction.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class TransactionRequestCreateDto {

    @NotNull
    @Size(min = 5, max = 20)
    private String reference;

    @NotNull
    @Size(max = 1)
    private String type;

    @NotEmpty
    @Valid
    private List<TransactionDetailsRequestDto> transactions;


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TransactionDetailsRequestDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDetailsRequestDto> transactions) {
        this.transactions = transactions;
    }
}
