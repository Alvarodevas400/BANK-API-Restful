package com.alvaro.bankapi.infrastructure.adapters.transaction.dto;

import java.math.BigDecimal;

public class TransactionResponseDto {

    private BigDecimal sequential;
    private int errorCode;
    private String errorDescription;

    public BigDecimal getSequential() {
        return sequential;
    }

    public void setSequential(BigDecimal sequential) {
        this.sequential = sequential;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
