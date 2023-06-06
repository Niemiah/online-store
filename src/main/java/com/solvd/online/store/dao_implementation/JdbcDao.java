package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.cart.OrderDetail;
import com.solvd.online.store.customers.User;
import com.solvd.online.store.dao_interface.BaseDao;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcDao<T, I extends Number> implements BaseDao<T> {
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

    public abstract OrderDetail find(Integer orderId, Integer productId);

    public abstract User find(Integer id);
}