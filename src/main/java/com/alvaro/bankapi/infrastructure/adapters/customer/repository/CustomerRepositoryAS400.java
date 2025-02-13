package com.alvaro.bankapi.infrastructure.adapters.customer.repository;

import com.alvaro.bankapi.SRVCLI300.ACT_CLIENT;
import com.alvaro.bankapi.SRVCLI300.DS_INPACTCLI;
import com.alvaro.bankapi.domain.customer.Customer;
import com.alvaro.bankapi.infrastructure.adapters.customer.mapper.CustomerMapper;
import com.alvaro.bankapi.infrastructure.config.AS400Connection;
import com.alvaro.bnkapi.SRVCLI000.GET_CLIENTEBYCODE;
import com.alvaro.bnkapi.SRVCLI100.GET_CLIBYID;
import com.alvaro.bnkapi.SRVCLI200.DS_INPREGCLI;
import com.alvaro.bnkapi.SRVCLI200.REG_CLIENT;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;

import java.math.BigDecimal;

@Repository
public class CustomerRepositoryAS400 {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepositoryAS400.class);

    private int code;
    private String message;

    @Autowired
    private AS400Connection as400Connection;

    public Customer GetCustomerByCode(int customerId) {
        logger.info("Calling AS400-SRVCLI000 - GetCustomerByCode with code: {}", customerId);
        return as400Connection.executeWithConnection(as400 -> {
            GET_CLIENTEBYCODE pgmSRVCLI000 = new GET_CLIENTEBYCODE(as400);
            code = pgmSRVCLI000.invoke(customerId);
            message = pgmSRVCLI000.getOU_MESSAGE();
            logger.info("AS400-SRVCLI000 - GetCustomerByCode response with code: {}", code);
            if (code == 0) {
                return CustomerMapper.fromGET_CLIENTEBYCODE(pgmSRVCLI000, customerId);
            } else {
                logger.error("Error AS400-SRVCLI000 - GetCustomerByCode getting customer with code: {}", customerId);
                return null;
            }
        });
    }

    public Customer GetCustomerByIdentification(String typeId, String identification) {
        logger.info("Calling AS400-SRVCLI100 - GetCustomerByIdentification with identification: {}", String.join("", typeId, identification));
        return as400Connection.executeWithConnection(as400 -> {
            GET_CLIBYID pgmSRVCLI100 = new GET_CLIBYID(as400);
            code = pgmSRVCLI100.invoke(typeId, identification);
            message = pgmSRVCLI100.getOU_MESSAGE();
            logger.info("AS400-SRVCLI100 - GetCustomerByIdentification response with code: {}", code);
            if (code == 0) {
                return CustomerMapper.fromGET_CLIBYID(pgmSRVCLI100);
            } else {
                logger.error("Error AS400-SRVCLI100 - GetCustomerByIdentification with identification: {}", String.join("", typeId, identification));
                return null;
            }
        });
    }

    public Integer SaveCustomer(DS_INPREGCLI newCustomer) {
        logger.info("Calling AS400-SRVCLI200 - SaveCustomer with data: {}", newCustomer.toString());
        return as400Connection.executeWithConnection(as400 -> {
            REG_CLIENT pgmSRVCLI200 = new REG_CLIENT(as400);
            code = pgmSRVCLI200.invoke(newCustomer);
            message = pgmSRVCLI200.getOU_MESSAGE();
            logger.info("AS400-SRVCLI200 - Called and response with code: {}", code);
            if (code == 0) {
                return pgmSRVCLI200.getOU_CODE().intValue();
            } else {
                logger.error("Error AS400-SRVCLI200: {}", newCustomer.toString());
                return 0;
            }
        });
    }
    public void UpdateCustomer(BigDecimal customerCode, DS_INPACTCLI likeCustomer) {
        logger.info("Calling AS400-SRVCLI300 - UpdateCustomer with data: {}, code: {}", likeCustomer.toString(), customerCode);
        as400Connection.executeWithConnection(as400 -> {
            ACT_CLIENT pgmSRVCLI300 = new ACT_CLIENT(as400);
            code = pgmSRVCLI300.invoke(customerCode, likeCustomer);
            message = pgmSRVCLI300.getOU_MESSAGE();
            logger.info("AS400-SRVCLI300 - Called and response with code: {}", code);
            if (code > 0) {
                logger.error("Error AS400-SRVCLI300: {}", message);
            }
            return null;
        });
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
