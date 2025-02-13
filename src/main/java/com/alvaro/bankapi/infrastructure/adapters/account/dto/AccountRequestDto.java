package com.alvaro.bankapi.infrastructure.adapters.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class AccountRequestDto {

    private BigDecimal customerCode;

    @NotBlank
    @Size(max = 4)
    private String accountType;
    @NotBlank
    @Size(max = 3)
    private String currency;
    @NotBlank
    @Size(max = 4)
    private String branch;
    @NotBlank
    @Size(max = 4)
    private String official;

    public BigDecimal getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(BigDecimal customerCode) {
        this.customerCode = customerCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }
}
