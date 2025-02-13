package com.alvaro.bankapi.infrastructure.adapters.customer.mapper;

import com.alvaro.bankapi.domain.customer.Customer;
import com.alvaro.bankapi.shared.CustomerType;
import com.alvaro.bankapi.shared.utils.Utils;
import com.alvaro.bnkapi.SRVCLI000.GET_CLIENTEBYCODE;
import com.alvaro.bnkapi.SRVCLI100.GET_CLIBYID;

import java.math.BigDecimal;

public class CustomerMapper {

    public static Customer fromGET_CLIENTEBYCODE(GET_CLIENTEBYCODE pgmAS400, int customerCode) {
        Customer customer = new Customer();
        customer.setCustomerCode(BigDecimal.valueOf(customerCode));
        String fullName = String.join(" ",
                pgmAS400.getOU_DATCLI().getPrimer_Apellido(),
                pgmAS400.getOU_DATCLI().getSegundo_Apellido(),
                pgmAS400.getOU_DATCLI().getPrimer_Nombre(),
                pgmAS400.getOU_DATCLI().getSegundo_Nombre()
        );

        customer.setFullName(fullName);
        customer.setFirstName(pgmAS400.getOU_DATCLI().getPrimer_Nombre());
        customer.setLastName(pgmAS400.getOU_DATCLI().getPrimer_Apellido());
        customer.setDateOfBirth(Utils.IntToLocalDate(pgmAS400.getOU_DATCLI().getFecha_Nacimiento().intValue()));
        customer.setId(pgmAS400.getOU_DATCLI().getIdentificacion());
        customer.setMaritalStatus(pgmAS400.getOU_DATCLI().getEstado_Civil());
        customer.setEmail(pgmAS400.getOU_DATCLI().getEmail());
        customer.setPhone(pgmAS400.getOU_DATCLI().getTelefono());
        customer.setStatus(pgmAS400.getOU_DATCLI().getEstado());
        customer.setIncomeType(pgmAS400.getOU_DATCLI().getIngresos_Fue());
        customer.setMonthIncome(pgmAS400.getOU_DATCLI().getIngresos_Men());
        customer.setOccupation(pgmAS400.getOU_DATCLI().getOcupacion());

        switch (pgmAS400.getOU_DATCLI().getTipo_Id()) {
            case CustomerType.CEDULA:
                customer.setTypeId(CustomerType.CEDULA_NAME);
                break;
            case CustomerType.PASAPORTE:
                customer.setTypeId(CustomerType.PASAPORTE_NAME);
                break;
        }
        customer.setCreatedAt(pgmAS400.getOU_DATCLI().getCreado_En());
        return customer;
    }

    public static Customer fromGET_CLIBYID(GET_CLIBYID pgmAS400){

        Customer customer = new Customer();
        customer.setCustomerCode(pgmAS400.getOU_DATCLI().getCODE());
        String fullName = String.join(" ",
                pgmAS400.getOU_DATCLI().getPRIMER_APELLIDO(),
                pgmAS400.getOU_DATCLI().getSEGUNDO_APELLIDO(),
                pgmAS400.getOU_DATCLI().getPRIMER_NOMBRE(),
                pgmAS400.getOU_DATCLI().getSEGUNDO_NOMBRE()
        );

        customer.setFullName(fullName);
        customer.setFirstName(pgmAS400.getOU_DATCLI().getPRIMER_NOMBRE());
        customer.setLastName(pgmAS400.getOU_DATCLI().getPRIMER_APELLIDO());
        customer.setDateOfBirth(Utils.IntToLocalDate(pgmAS400.getOU_DATCLI().getFECHA_NACIMIENTO().intValue()));
        customer.setId(pgmAS400.getOU_DATCLI().getIDENTIFICACION());
        customer.setMaritalStatus(pgmAS400.getOU_DATCLI().getESTADO_CIVIL());
        customer.setEmail(pgmAS400.getOU_DATCLI().getEMAIL());
        customer.setPhone(pgmAS400.getOU_DATCLI().getTELEFONO());
        customer.setStatus(pgmAS400.getOU_DATCLI().getESTADO());
        customer.setIncomeType(pgmAS400.getOU_DATCLI().getINGRESOS_FUE());
        customer.setMonthIncome(pgmAS400.getOU_DATCLI().getINGRESOS_MEN());
        customer.setOccupation(pgmAS400.getOU_DATCLI().getOCUPACION());

        switch (pgmAS400.getOU_DATCLI().getTIPO_ID()) {
            case CustomerType.CEDULA:
                customer.setTypeId(CustomerType.CEDULA_NAME);
                break;
            case CustomerType.PASAPORTE:
                customer.setTypeId(CustomerType.PASAPORTE_NAME);
                break;
        }
        customer.setCreatedAt(pgmAS400.getOU_DATCLI().getCREADO_EN());
        return customer;
    }



}
