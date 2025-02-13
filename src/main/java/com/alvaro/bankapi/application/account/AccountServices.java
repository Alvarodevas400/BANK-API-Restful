package com.alvaro.bankapi.application.account;

import com.alvaro.bankapi.SRVCTA200.DS_INPSAVACCOUNT;
import com.alvaro.bankapi.domain.account.Account;
import com.alvaro.bankapi.infrastructure.adapters.account.dto.AccountRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.account.dto.AccountSmallDto;
import com.alvaro.bankapi.infrastructure.adapters.account.repository.AccountRepositoryAS400;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServices {

    private int code;
    private String message;

    @Autowired
    private AccountRepositoryAS400 accountRepository;

    public Account getAccountDetails(BigDecimal accountNumber){
        Account accountInfo = accountRepository.getAccountByNumber(accountNumber);
        code = accountRepository.getCode();
        message = accountRepository.getMessage();
        return accountInfo;
    }

    public List<AccountSmallDto> getAllAccountsByCustomer(int customerCode){
        List<Account> responseAccounts;
        List<AccountSmallDto> returnsAccounts = new ArrayList<>();
        responseAccounts = accountRepository.getAllAccountsByCustomer(customerCode);
        code = accountRepository.getCode();
        message = accountRepository.getMessage();

        responseAccounts.forEach( account -> {
            AccountSmallDto acc = new AccountSmallDto();
            acc.setAccount(account.getAccount());
            acc.setAvailable(account.getAvailable());
            acc.setCurrency(account.getCurrency());
            acc.setType(account.getType());
            acc.setStatus(account.getStatus());
            returnsAccounts.add(acc);
        });
        return  returnsAccounts;
    }

    public BigDecimal createAccount(AccountRequestDto dto){
        DS_INPSAVACCOUNT accountAS400 =  AccountServiceMapper.toAccountAS400(dto);
        BigDecimal newAccountNumber = accountRepository.createAccount(accountAS400);
        code = accountRepository.getCode();
        message = accountRepository.getMessage();
        return newAccountNumber;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
