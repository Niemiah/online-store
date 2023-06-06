package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.merchandise.Product;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.online.store.ConnectionPool.getConnection;

public class ProductDaoImpl extends JdbcDao<Product, Integer> {
    public ProductDaoImpl(BasicDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Product mapRowToEntity(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setProductDescription(resultSet.getString("product_description"));
        product.setProductPrice(resultSet.getBigDecimal("product_price"));
        return product;
    }

    @Override
    protected String getTableName() {
        return "Products";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Products (product_name, product_description, product_price) VALUES (?, ?, ?)";
    }

    @Override
    protected String getFindQuery() {
        return "SELECT * FROM Products WHERE product_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM Products";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Products SET product_name = ?, product_description = ?, product_price = ? WHERE product_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Products WHERE product_id = ?";
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getProductName());
        statement.setString(2, product.getProductDescription());
        statement.setBigDecimal(3, product.getProductPrice());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getProductName());
        statement.setString(2, product.getProductDescription());
        statement.setBigDecimal(3, product.getProductPrice());
        statement.setInt(4, product.getProductId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Product product) throws SQLException {
        statement.setInt(1, product.getProductId());
    }

    @Override
    public Product create(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            prepareStatementForCreate(statement, product);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product find(Integer id) {
        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getFindQuery())) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = mapRowToEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                products.add(mapRowToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product update(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, product);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating product failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void delete(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, product);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting product failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}