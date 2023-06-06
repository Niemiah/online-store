package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.merchandise.Category;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.online.store.ConnectionPool.getConnection;

public class CategoryDaoImpl extends JdbcDao<Category, Integer> {
    public CategoryDaoImpl(BasicDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Category mapRowToEntity(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setCategoryId(resultSet.getInt("category_id"));
        category.setCategoryName(resultSet.getString("category_name"));
        return category;
    }

    @Override
    protected String getTableName() {
        return "Categories";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Categories (category_name) VALUES (?)";
    }

    @Override
    protected String getFindQuery() {
        return "SELECT * FROM Categories WHERE category_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM Categories";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Categories SET category_name = ? WHERE category_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Categories WHERE category_id = ?";
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Category category) throws SQLException {
        statement.setString(1, category.getCategoryName());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Category category) throws SQLException {
        statement.setString(1, category.getCategoryName());
        statement.setInt(2, category.getCategoryId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Category category) throws SQLException {
        statement.setInt(1, category.getCategoryId());
    }

    @Override
    public Category create(Category category) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            prepareStatementForCreate(statement, category);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating category failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setCategoryId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating category failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public Category find(Integer id) {
        Category category = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getFindQuery())) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    category = mapRowToEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getFindAllQuery());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                categories.add(mapRowToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category update(Category category) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, category);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating category failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public void delete(Category category) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, category);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting category failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}