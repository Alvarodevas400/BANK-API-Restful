package com.alvaro.bankapi.infrastructure.adapters.transaction.mappers;

import com.alvaro.bankapi.SRVTRN000.DS_TRNMOVE;
import com.alvaro.bankapi.SRVTRN000.LST_TRANS;
import com.alvaro.bankapi.domain.transaction.Transaction;
import com.alvaro.bankapi.shared.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionMapper {

    public static List<Transaction> fromLST_TRANS(LST_TRANS pgmAS400) {

        List<Transaction> lstTransaction = new ArrayList<>();

        for (DS_TRNMOVE transactionAS400 : pgmAS400.getOU_MOVCTA().getLST_TRN()) {
            if (transactionAS400.getVALORTRANSAC().compareTo(BigDecimal.ZERO) != 0) {
                Transaction transactionDomain = new Transaction();
                transactionDomain.setDescription(transactionAS400.getDESCRTRANSAC());
                transactionDomain.setAmount(transactionAS400.getVALORTRANSAC());
                transactionDomain.setDate(Utils.IntToLocalDate(transactionAS400.getFECHATRANSAC().intValue()));
                transactionDomain.setTime(Utils.convertBigDecimalToLocalTime(transactionAS400.getHORATRANSAC()));
                if (transactionAS400.getSIGNOTRANSAC().equalsIgnoreCase("-")) {
                    transactionDomain.setAmount(transactionAS400.getVALORTRANSAC().negate());
                }
                lstTransaction.add(transactionDomain);
            } else {
                break;
            }
        }
        return lstTransaction;
    }

}
