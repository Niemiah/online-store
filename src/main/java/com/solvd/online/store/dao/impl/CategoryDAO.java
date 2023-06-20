package com.solvd.online.store.dao.impl;
import com.solvd.online.store.model.Category;
import com.solvd.online.store.dao.ICategoryDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.online.store.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO implements ICategoryDAO {
    private final static Logger LOGGER = LogManager.getLogger(CategoryDAO.class);
    private static final String INSERT_QUERY = "INSERT INTO Category (categoryId, categoryName) VALUES (?,?)";
    private static final String UPDATE_QUERY = "UPDATE Category SET categoryName=? WHERE categoryId=?";
    private static final String DELETE_QUERY = "DELETE FROM Category WHERE categoryId=?";
    private static final String GET_QUERY = "SELECT * FROM Category WHERE categoryId=?";

    @Override
    public void insert(Category category) {
        if (category == null) {
            LOGGER.error("Category object is null.");
            throw new IllegalArgumentException("Category object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, category.getCategoryId());
            preparedStatement.setString(2, category.getCategoryName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public void update(Category category) {
        if (category == null) {
            LOGGER.error("Category object is null.");
            throw new IllegalArgumentException("Category object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setInt(2, category.getCategoryId());
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
    public Category getById(int id) {
        Category category = new Category();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    category.setCategoryId(resultSet.getInt("categoryId"));
                    category.setCategoryName(resultSet.getString("categoryName"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException("Unable to obtain resource.", e);
        }
        return category;
    }
}