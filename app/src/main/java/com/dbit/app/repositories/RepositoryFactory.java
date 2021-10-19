package com.dbit.app.repositories;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class RepositoryFactory {
    private static final RepositoryTypes TYPE;

    static {
        Properties appProperties = new Properties();
        try {
            appProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        TYPE = RepositoryTypes.getTypeByStr(appProperties.getProperty("repository.type"));
    }

    private RepositoryFactory() {
        //factory empty private
    }

    public static EmployeeRepository getEmployeeRepository() {
        switch (TYPE) {
            case MEMORY:
            default:
                return EmployeeRepositoryInMemory.getInstance();
        }
    }
}
