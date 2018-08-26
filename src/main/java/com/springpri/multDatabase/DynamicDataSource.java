package com.springpri.multDatabase;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

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