package com.alvaro.bankapi.infrastructure.adapters.transaction.repository;

import com.alvaro.bankapi.SRVTRN000.DS_INPLSTTRN;
import com.alvaro.bankapi.SRVTRN000.LST_TRANS;
import com.alvaro.bankapi.SRVTRN100.DS_INPREGTRN;
import com.alvaro.bankapi.SRVTRN100.DS_RESREGTRN;
import com.alvaro.bankapi.SRVTRN100.PROC_TRAN;
import com.alvaro.bankapi.domain.transaction.Transaction;
import com.alvaro.bankapi.infrastructure.adapters.transaction.mappers.TransactionMapper;
import com.alvaro.bankapi.infrastructure.config.AS400Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepositoryAS400 {

    private static final Logger logger = LoggerFactory.getLogger(TransactionRepositoryAS400.class);

    private int code;
    private String message;

    @Autowired
    private AS400Connection as400Connection;

    public List<Transaction> getTransactions(DS_INPLSTTRN filters) {
        logger.info("Calling AS400 pgm SRVTRN000 with data: {}", filters.toString());
        return as400Connection.executeWithConnection(as400 -> {
            LST_TRANS pgmSRVTRN000 = new LST_TRANS(as400);
            code = pgmSRVTRN000.invoke(filters);
            message = pgmSRVTRN000.getOU_MESSAGE();
            logger.info("AS400 pgm SRVTRN000 response with code: {}", code);
            if (code == 0) {
                return TransactionMapper.fromLST_TRANS(pgmSRVTRN000);
            } else {
                logger.error("Error - AS400 SRVTRN000 with data: {}", filters);
                return null;
            }
        });
    }

    public List<DS_RESREGTRN> createTransaction(DS_INPREGTRN transactions) {
        logger.info("Calling AS400 pgm SRVTRN100 with data: {}", transactions);
        return as400Connection.executeWithConnection(as400 -> {
            PROC_TRAN pgmSRVTRN100 = new PROC_TRAN(as400);
            code = pgmSRVTRN100.invoke(transactions);
            message = pgmSRVTRN100.getOU_MESSAGE();
            logger.info("AS400 pgm SRVTRN100 response with code: {}", code);
            if (code == 0) {
                return null;
            } else {
                logger.error("Error - AS400 SRVTRN100 with data: {}", transactions);
                return pgmSRVTRN100.getOU_RESPONSE().getLST_RESPONSE();
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
