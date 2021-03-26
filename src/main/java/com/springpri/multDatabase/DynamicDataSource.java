package com.springpri.multDatabase;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.logging.Logger;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {

        return DataSourceContextHolder.getDataSourceType();
    }

    @Override
    public Logger getParentLogger() {
        // TODO Auto-generated method stub
        return null;
    }
}