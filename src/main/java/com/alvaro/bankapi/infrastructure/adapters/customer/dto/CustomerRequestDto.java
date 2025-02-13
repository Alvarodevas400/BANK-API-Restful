package com.alvaro.bankapi.infrastructure.adapters.customer.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerRequestDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    private String middleName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;
    private String lastName2;

    @NotBlank
    @Size(max = 1)
    private String typeId;
    @NotBlank
    private String id;

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(max = 8)
    private String phone;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotBlank
    private String maritalStatus;

    @NotNull
    @PositiveOrZero
    private BigDecimal monthIncome;

    @NotBlank
    @Size(max = 4)
    private String incomeType;

    public CustomerRequestDto(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public BigDecimal getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(BigDecimal monthIncome) {
        this.monthIncome = monthIncome;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }
}
