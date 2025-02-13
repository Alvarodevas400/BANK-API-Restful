package com.alvaro.bankapi.infrastructure.adapters.account.repository;

import com.alvaro.bankapi.SRVCTA000.GET_INFACC;
import com.alvaro.bankapi.SRVCTA100.LST_ALLACC;
import com.alvaro.bankapi.SRVCTA200.DS_INPSAVACCOUNT;
import com.alvaro.bankapi.SRVCTA200.SAV_CUENTA;
import com.alvaro.bankapi.domain.account.Account;
import com.alvaro.bankapi.infrastructure.adapters.account.mappers.AccountMappers;
import com.alvaro.bankapi.infrastructure.adapters.customer.repository.CustomerRepositoryAS400;
import com.alvaro.bankapi.infrastructure.config.AS400Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryAS400 {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryAS400.class);

    private int code;
    private String message;

    @Autowired
    private AS400Connection as400Connection;


    public Account getAccountByNumber(BigDecimal accountNumber){
        logger.info("Calling AS400 pgm SRVCTA000 with number: {}", accountNumber);
        return as400Connection.executeWithConnection(as400 -> {
            GET_INFACC pgmSRVCTA000 = new GET_INFACC(as400);
            code = pgmSRVCTA000.invoke(accountNumber);
            message = pgmSRVCTA000.getOU_MESSAGE();
            logger.info("AS400 SRVCTA000 response with code: {}", code);
            if (code == 0){
                return AccountMappers.fromGET_INFACC(pgmSRVCTA000, accountNumber);
            }else{
                logger.error("Error - AS400 SRVCTA000 with number: {}", accountNumber);
                return null;
            }
        });
    }

    public List<Account> getAllAccountsByCustomer(int customerCode){
        logger.info("Calling AS400 pgm SRVCTA100 with customerCode: {}", customerCode);
        return as400Connection.executeWithConnection(as400 -> {
            LST_ALLACC pgmSRVCTA100 = new LST_ALLACC(as400);
            code = pgmSRVCTA100.invoke(BigDecimal.valueOf(customerCode));
            message = pgmSRVCTA100.getOU_MESSAGE();
            logger.info("AS400 SRVCTA100 response with code: {}", code);
            if (code == 0){
                return AccountMappers.fromLST_ALLACC(pgmSRVCTA100);
            }else{
                logger.error("Error - AS400 SRVCTA100 with customerCode: {}", customerCode);
                return null;
            }
        });
    }

    public BigDecimal createAccount(DS_INPSAVACCOUNT newAccount){
        logger.info("Calling AS400 pgm SRVCTA200 with data: {}", newAccount);
        return as400Connection.executeWithConnection(as400 -> {
            SAV_CUENTA pgmSRVCTA200 = new SAV_CUENTA(as400);
            code = pgmSRVCTA200.invoke(newAccount);
            message = pgmSRVCTA200.getOU_MESSAGE();
            logger.info("AS400 SRVCTA200 response with code: {}", code);
            if (code == 0){
                return pgmSRVCTA200.getOU_CUENTA();
            }else{
                logger.error("Error - AS400 SRVCTA200 {}", message);
                return null;
            }
        });




    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
