package com.solvd.online.store.impl;
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
    private static final String INSERT = "INSERT INTO Product (productId, productName, productDescription, productPrice) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE Product SET productName=?, productDescription=?, productPrice=? WHERE productId=?";
    private static final String DELETE = "DELETE FROM Product WHERE productId=?";
    private static final String GET = "SELECT * FROM Product WHERE productId=?";

    @Override
    public void insert(Product product) {
        if(product == null){
            LOGGER.error("Product Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setBigDecimal(4, product.getProductPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product product) {
        if(product == null){
            LOGGER.error("Product Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setBigDecimal(3, product.getProductPrice());
            preparedStatement.setInt(4, product.getProductId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getById(int id) {
        Product product = new Product();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    product.setProductId(resultSet.getInt("productId"));
                    product.setProductName(resultSet.getString("productName"));
                    product.setProductDescription(resultSet.getString("productDescription"));
                    product.setProductPrice(resultSet.getBigDecimal("productPrice"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException(e);
        }
        return product;
    }
}