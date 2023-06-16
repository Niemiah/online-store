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
    private static final String INSERT = "INSERT INTO Category (categoryId, categoryName) VALUES (?,?)";
    private static final String UPDATE = "UPDATE Category SET categoryName=? WHERE categoryId=?";
    private static final String DELETE = "DELETE FROM Category WHERE categoryId=?";
    private static final String GET = "SELECT * FROM Category WHERE categoryId=?";

    @Override
    public void insert(Category category) {
        if(category == null){
            LOGGER.error("Category Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setInt(1, category.getCategoryId());
            preparedStatement.setString(2, category.getCategoryName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Category category) {
        if(category == null){
            LOGGER.error("Category Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setInt(2, category.getCategoryId());
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
    public Category getById(int id) {
        Category category = new Category();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    category.setCategoryId(resultSet.getInt("categoryId"));
                    category.setCategoryName(resultSet.getString("categoryName"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException(e);
        }
        return category;
    }
}