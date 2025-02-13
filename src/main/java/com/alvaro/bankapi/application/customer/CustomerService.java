package com.alvaro.bankapi.application.customer;

import com.alvaro.bankapi.SRVCLI300.DS_INPACTCLI;
import com.alvaro.bankapi.domain.customer.Customer;
import com.alvaro.bankapi.infrastructure.adapters.customer.dto.CustomerRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.customer.dto.CustomerUpdateRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.customer.repository.CustomerRepositoryAS400;
import com.alvaro.bnkapi.SRVCLI200.DS_INPREGCLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerService {

    private int code;
    private String message;

    @Autowired
    private CustomerRepositoryAS400 customerRepository;

    public Customer GetCustomerByCode(int customerId) {
        Customer customer = customerRepository.GetCustomerByCode(customerId);
        code = customerRepository.getCode();
        message = customerRepository.getMessage();
        return customer;
    }

    public Customer GetCustomerByIdentification(String typeId, String identification) {
        Customer customer = customerRepository.GetCustomerByIdentification(typeId, identification);
        code = customerRepository.getCode();
        message = customerRepository.getMessage();
        return customer;
    }

    public Integer SaveCustomer(CustomerRequestDto newCustomer) {
        DS_INPREGCLI customerAS400 = CustomerServiceMapper.toCustomerAS400(newCustomer);
        int newCustomerCode = customerRepository.SaveCustomer(customerAS400);
        code = customerRepository.getCode();
        message = customerRepository.getMessage();
        return newCustomerCode;
    }

    public void UpdateCustomer(int customerCode, CustomerUpdateRequestDto updateCustomer){
        DS_INPACTCLI customerAS400 = CustomerServiceMapper.toCustomerUpdAS400(updateCustomer);
        customerRepository.UpdateCustomer(BigDecimal.valueOf(customerCode),customerAS400);
        code = customerRepository.getCode();
        message = customerRepository.getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
