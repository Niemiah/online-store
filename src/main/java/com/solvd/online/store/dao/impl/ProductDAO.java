package com.solvd.online.store.dao.impl;
import com.solvd.online.store.dao.IProductDAO;
import com.solvd.online.store.model.Product;
import com.solvd.online.store.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO implements IProductDAO {
    private final static Logger LOGGER = LogManager.getLogger(ProductDAO.class);
    private static final String INSERT_QUERY = "INSERT INTO Product (productId, productName, productDescription, productPrice) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE Product SET productName=?, productDescription=?, productPrice=? WHERE productId=?";
    private static final String DELETE_QUERY = "DELETE FROM Product WHERE productId=?";
    private static final String GET_QUERY = "SELECT * FROM Product WHERE productId=?";

    @Override
    public void insert(Product product) {
        if (product == null) {
            LOGGER.error("Product object is null.");
            throw new IllegalArgumentException("Product object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setBigDecimal(4, product.getProductPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            LOGGER.error("Product object is null.");
            throw new IllegalArgumentException("Product object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setBigDecimal(3, product.getProductPrice());
            preparedStatement.setInt(4, product.getProductId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public Product getById(int id) {
        Product product = new Product();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product.setProductId(resultSet.getInt("productId"));
                    product.setProductName(resultSet.getString("productName"));
                    product.setProductDescription(resultSet.getString("productDescription"));
                    product.setProductPrice(resultSet.getBigDecimal("productPrice"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException("Unable to obtain resource.", e);
        }
        return product;
    }
}