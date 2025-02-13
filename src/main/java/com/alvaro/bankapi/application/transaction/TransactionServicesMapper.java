package com.alvaro.bankapi.application.transaction;

import com.alvaro.bankapi.SRVTRN000.DS_INPLSTTRN;
import com.alvaro.bankapi.SRVTRN100.DS_INPREGTRN;
import com.alvaro.bankapi.SRVTRN100.DS_REGTRN;
import com.alvaro.bankapi.SRVTRN100.DS_RESREGTRN;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionDetailsRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionRequestCreateDto;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.transaction.dto.TransactionResponseDto;
import com.alvaro.bankapi.shared.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TransactionServicesMapper {

    public static DS_INPLSTTRN toFilterAS400(TransactionRequestDto dto) {
        DS_INPLSTTRN filterAS400 = new DS_INPLSTTRN();
        filterAS400.setCUENTA(dto.getAccount());
        filterAS400.setPAGE(dto.getPage());
        filterAS400.setFECHADESDE(Utils.convertLocalDateToBigDecimal(dto.getDateFrom()));
        filterAS400.setFECHAHASTA(Utils.convertLocalDateToBigDecimal(dto.getDateTo()));
        return filterAS400;
    }

    public static DS_INPREGTRN toTransactionAS400(TransactionRequestCreateDto dto) {
        DS_INPREGTRN transactionAS400 = new DS_INPREGTRN();
        DS_REGTRN[] lst_transactionsAS400 = new DS_REGTRN[6];
        int i = 0;

        transactionAS400.setREFERENCIA(dto.getReference());
        transactionAS400.setTYPTRANSAC(dto.getType());

        for (TransactionDetailsRequestDto transaction : dto.getTransactions()) {
            DS_REGTRN trnDetailAS400 = getDsRegtrn(transaction);
            lst_transactionsAS400[i] = trnDetailAS400;
            i++;
        }

        for (int j = i; j < 6; j++) {
            lst_transactionsAS400[j] = new DS_REGTRN();
        }
        transactionAS400.setLST_TRANSA(lst_transactionsAS400);
        return transactionAS400;
    }


    public static List<TransactionResponseDto> fromTransactionRepository(List<DS_RESREGTRN> responseAS400) {
        List<TransactionResponseDto> lst_responseTransactions = new ArrayList<>();
        if (responseAS400 != null) {
            for (DS_RESREGTRN response : responseAS400) {
                if (response.getERRORCOD() > 0) {
                    TransactionResponseDto responseService = new TransactionResponseDto();
                    responseService.setErrorCode(response.getERRORCOD());
                    responseService.setSequential(response.getSECUENCIA());
                    responseService.setErrorDescription(response.getERRORDSC());
                    lst_responseTransactions.add(responseService);
                }
            }
        }
        return lst_responseTransactions;
    }

    private static DS_REGTRN getDsRegtrn(TransactionDetailsRequestDto transaction) {
        DS_REGTRN trnDetailAS400 = new DS_REGTRN();
        trnDetailAS400.setTIPO(transaction.getCreditOrDebit());
        trnDetailAS400.setCUENTA(transaction.getAccount());
        trnDetailAS400.setAMOTRANSACC(transaction.getAmount());
        trnDetailAS400.setCODTRANSACC(transaction.getCode());
        trnDetailAS400.setDSCTRANSACC(transaction.getDescription());
        trnDetailAS400.setCCYTRANSACC(transaction.getCurrency());
        trnDetailAS400.setSECUENCIA(transaction.getSequential());
        return trnDetailAS400;
    }
}
