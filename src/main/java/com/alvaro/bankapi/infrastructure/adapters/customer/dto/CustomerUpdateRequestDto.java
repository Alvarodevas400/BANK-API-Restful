package com.alvaro.bankapi.infrastructure.adapters.customer.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerUpdateRequestDto {


    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    private String middleName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;
    private String lastName2;

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

    @NotBlank
    @Size(min = 3, max = 30)
    private String occupation;

    public CustomerUpdateRequestDto() {
    }

    public @NotBlank @Size(min = 3, max = 20) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank @Size(min = 3, max = 20) String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public @NotBlank @Size(min = 3, max = 20) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank @Size(min = 3, max = 20) String lastName) {
        this.lastName = lastName;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @NotBlank @Size(max = 8) String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank @Size(max = 8) String phone) {
        this.phone = phone;
    }

    public @NotNull @Past LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotNull @Past LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotBlank String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(@NotBlank String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public @NotNull @PositiveOrZero BigDecimal getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(@NotNull @PositiveOrZero BigDecimal monthIncome) {
        this.monthIncome = monthIncome;
    }

    public @NotBlank @Size(max = 4) String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(@NotBlank @Size(max = 4) String incomeType) {
        this.incomeType = incomeType;
    }

    public @NotBlank @Size(min = 3, max = 30) String getOccupation() {
        return occupation;
    }

    public void setOccupation(@NotBlank @Size(min = 3, max = 30) String occupation) {
        this.occupation = occupation;
    }
}
