package com.alvaro.bankapi.infrastructure.config;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400ConnectionPool;
import com.ibm.as400.access.ConnectionPoolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class AS400Connection {

    private final AS400ConnectionPool connectionPool;
    private final Environment environment;

    @Autowired
    public AS400Connection(AS400ConnectionPool connectionPool, Environment environment) {
        this.connectionPool = connectionPool;
        this.environment = environment;
    }

    //Funcion de Orden Superior
    public <T> T executeWithConnection(Function<AS400, T> action){
        AS400 as400 = null;
        try {
            as400 = getConnection();
            return action.apply(as400);
        }finally {
            assert as400 != null;
            //as400.disconnectAllServices(); //La recomendada
            releaseConnection(as400);
        }
    }

    private AS400 getConnection() {
        try {
            return connectionPool.getConnection(
                    environment.getProperty("as400.system"),
                    environment.getProperty("as400.user"),
                    Objects.requireNonNull(environment.getProperty("as400.password")).toCharArray()
            );
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Error getting AS400 connection", e);
        }
    }

    private void releaseConnection(AS400 as400){
        connectionPool.returnConnectionToPool(as400);
    }
}
