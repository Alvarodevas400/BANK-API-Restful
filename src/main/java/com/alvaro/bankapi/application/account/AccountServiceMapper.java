package com.alvaro.bankapi.application.account;

import com.alvaro.bankapi.SRVCTA200.DS_INPSAVACCOUNT;
import com.alvaro.bankapi.infrastructure.adapters.account.dto.AccountRequestDto;

public class AccountServiceMapper {
    public static DS_INPSAVACCOUNT toAccountAS400(AccountRequestDto dto){
        DS_INPSAVACCOUNT accountAS400 = new DS_INPSAVACCOUNT();
        accountAS400.setTIPO(dto.getAccountType());
        accountAS400.setOFICIAL(dto.getOfficial());
        accountAS400.setMONEDA(dto.getCurrency());
        accountAS400.setAGENCIA(dto.getBranch());
        accountAS400.setCODE(dto.getCustomerCode());
        return accountAS400;
    }

}
