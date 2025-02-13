package com.alvaro.bankapi.application.customer;

import com.alvaro.bankapi.SRVCLI300.DS_INPACTCLI;
import com.alvaro.bankapi.infrastructure.adapters.customer.dto.CustomerRequestDto;
import com.alvaro.bankapi.infrastructure.adapters.customer.dto.CustomerUpdateRequestDto;
import com.alvaro.bankapi.shared.utils.Utils;
import com.alvaro.bnkapi.SRVCLI200.DS_ADFIELDS;
import com.alvaro.bnkapi.SRVCLI200.DS_INPREGCLI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceMapper {

    public static DS_INPREGCLI toCustomerAS400(CustomerRequestDto dto){
        int castDate;
        DS_INPREGCLI customerAS400 = new DS_INPREGCLI();

        customerAS400.setPRIMER_NOMBRE(dto.getFirstName());
        customerAS400.setSEGUNDO_NOMBRE(dto.getMiddleName());
        customerAS400.setPRIMER_APELLIDO(dto.getLastName());
        customerAS400.setSEGUNDO_APELLIDO(dto.getLastName2());
        customerAS400.setTIPO_ID(dto.getTypeId());
        customerAS400.setIDENTIFICACION(dto.getId());
        customerAS400.setEMAIL(dto.getEmail());
        customerAS400.setTELEFONO(dto.getPhone());
        customerAS400.setESTADO_CIVIL(dto.getMaritalStatus());
        customerAS400.setINGRESOS_FUE(dto.getIncomeType());
        customerAS400.setINGRESOS_MEN(dto.getMonthIncome());
        castDate = Utils.LocateToInt(dto.getDateOfBirth());
        customerAS400.setFECHA_NACIMIENTO(BigDecimal.valueOf(castDate));
        customerAS400.setLSTCAMPOSADI(initializeAdditionalFields());
        return customerAS400;
    }

    public static DS_INPACTCLI toCustomerUpdAS400(CustomerUpdateRequestDto dto){
        DS_INPACTCLI customerAS400 = new DS_INPACTCLI();
        customerAS400.setPRIMER_NOMBRE(dto.getFirstName());
        customerAS400.setSEGUNDO_NOMBRE(dto.getMiddleName());
        customerAS400.setPRIMER_APELLIDO(dto.getLastName());
        customerAS400.setSEGUNDO_APELLIDO(dto.getLastName2());
        customerAS400.setEMAIL(dto.getEmail());
        customerAS400.setTELEFONO(dto.getPhone());
        customerAS400.setESTADO_CIVIL(dto.getMaritalStatus());
        customerAS400.setFECHA_NACIMIENTO(BigDecimal.valueOf(Utils.LocateToInt(dto.getDateOfBirth())));
        customerAS400.setOCUPACION(dto.getOccupation());
        customerAS400.setINGRESOS_MEN(dto.getMonthIncome());
        customerAS400.setINGRESOS_FUE(dto.getIncomeType());

        return customerAS400;

    }

    private static List<DS_ADFIELDS> initializeAdditionalFields(){
        List<DS_ADFIELDS> lstAdfields = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            DS_ADFIELDS adicional = new DS_ADFIELDS();
            adicional.setKey("");
            adicional.setValue("");
            lstAdfields.add(adicional);
        }
        return lstAdfields;
    }

}
