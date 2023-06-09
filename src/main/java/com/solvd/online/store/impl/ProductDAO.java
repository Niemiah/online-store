package com.solvd.online.store.impl;
import com.solvd.online.store.dao.IProductDAO;
import com.solvd.online.store.model.Product;
import com.solvd.online.store.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductDAO implements IProductDAO {
    private final static Logger LOGGER = LogManager.getLogger(ProductDAO.class);
    private static final String INSERT = "INSERT INTO Product (productId, productName, productDescription, productPrice) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE Product SET productName=?, productDescription=?, productPrice=? WHERE productId=?";
    private static final String DELETE = "DELETE FROM Product WHERE productId=?";
    private static final String GET = "SELECT * FROM Product WHERE productId=?";

    @Override
    public void insert(Product product) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if(product == null){
            LOGGER.error("Product Object is null.");
            throw new NullPointerException();
        }
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setDouble(4, product.getProductPrice().doubleValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            closeResources(connectionPool, connection, preparedStatement);
        }
    }

    @Override
    public void update(Product product) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if(product == null){
            LOGGER.error("Product Object is null.");
            throw new NullPointerException();
        }
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setDouble(3, product.getProductPrice().doubleValue());
            preparedStatement.setInt(4, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            closeResources(connectionPool, connection, preparedStatement);
        }
    }

    @Override
    public void deleteById(int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            closeResources(connectionPool, connection, preparedStatement);
        }
    }

    @Override
    public Product getById(int id) {
        Product product = new Product();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(GET);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                product.setProductId(resultSet.getInt("productId"));
                product.setProductName(resultSet.getString("productName"));
                product.setProductDescription(resultSet.getString("productDescription"));
                product.setProductPrice(BigDecimal.valueOf(resultSet.getDouble("productPrice")));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Unable to close resource.");
                throw new RuntimeException(e);
            }
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return product;
    }

    private void closeResources(ConnectionPool connectionPool, Connection connection, PreparedStatement preparedStatement){
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to close Prepared Statement.");
            throw new RuntimeException(e);
        }
        if (connection != null) {
            connectionPool.releaseConnection(connection);
        }
    }
}