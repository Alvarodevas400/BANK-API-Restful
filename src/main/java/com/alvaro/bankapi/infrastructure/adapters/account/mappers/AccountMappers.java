package com.alvaro.bankapi.infrastructure.adapters.account.mappers;

import com.alvaro.bankapi.SRVCTA000.GET_INFACC;
import com.alvaro.bankapi.SRVCTA100.DS_CUENTA;
import com.alvaro.bankapi.SRVCTA100.LST_ALLACC;
import com.alvaro.bankapi.domain.account.Account;
import com.alvaro.bankapi.shared.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountMappers {

    public static Account fromGET_INFACC(GET_INFACC pgmAS400, BigDecimal accountNumber) {
        Account account = new Account();
        BigDecimal workdate;

        account.setAccount(accountNumber);
        account.setCustomerCode(pgmAS400.getOU_DATCTA().getCODIGO_CLIENTE());
        account.setType(pgmAS400.getOU_DATCTA().getTIPO());
        account.setCurrency(pgmAS400.getOU_DATCTA().getMONEDA());
        account.setAvailable(pgmAS400.getOU_DATCTA().getDISPONIBLE());
        account.setStatus(pgmAS400.getOU_DATCTA().getESTADO());
        account.setBranch(pgmAS400.getOU_DATCTA().getAGENCIA());
        account.setOfficial(pgmAS400.getOU_DATCTA().getOFICIAL());
        workdate = pgmAS400.getOU_DATCTA().getFECHA_APERTURA();
        account.setOpenDate(Utils.IntToLocalDate(workdate.intValue()));

        if (pgmAS400.getOU_DATCTA().getFECHA_CIERRE().intValue() > 0) {
            workdate = pgmAS400.getOU_DATCTA().getFECHA_CIERRE();
            account.setCloseDate(Utils.IntToLocalDate(workdate.intValue()));
        }

        if (pgmAS400.getOU_DATCTA().getFECHA_ULT_MOV().intValue() > 0) {
            workdate = pgmAS400.getOU_DATCTA().getFECHA_ULT_MOV();
            account.setLastTransaction(Utils.IntToLocalDate(workdate.intValue()));
        }
        return account;
    }


    public static List<Account> fromLST_ALLACC(LST_ALLACC pgmAS400){
        List<Account> lstAccount = new ArrayList<>();
        BigDecimal zeroBigdecimal = new BigDecimal("0");

        for(DS_CUENTA accounts : pgmAS400.getOU_DATCTA().getLST_CUENTAS()){
            if (accounts.getCUENTA().compareTo(zeroBigdecimal) > 0){
                Account account = new Account();
                account.setType( accounts.getTIPO());
                account.setCurrency(accounts.getMONEDA());
                account.setAvailable(accounts.getDISPONIBLE());
                account.setStatus(accounts.getESTADO());
                account.setAccount(accounts.getCUENTA());
                lstAccount.add(account);
            }else {
                break;
            }
        }
        return lstAccount;
    }

}
