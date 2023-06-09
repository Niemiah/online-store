package com.solvd.db.impl;
import com.solvd.db.dao.DAO;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcDao<T, I extends Number> implements DAO<T> {
    protected final BasicDataSource dataSource;

    public JdbcDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected abstract T mapRowToEntity(ResultSet resultSet) throws SQLException;
    protected abstract String getTableName();
    protected abstract String getCreateQuery();
    protected abstract String getFindQuery();
    protected abstract String getFindAllQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract void prepareStatementForCreate(PreparedStatement statement, T t) throws SQLException;
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T t) throws SQLException;
    protected abstract void prepareStatementForDelete(PreparedStatement statement, T t) throws SQLException;

}