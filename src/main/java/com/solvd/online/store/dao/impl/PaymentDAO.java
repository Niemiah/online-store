package com.solvd.online.store.dao.impl;
import com.solvd.online.store.dao.IPaymentDAO;
import com.solvd.online.store.model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.online.store.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO implements IPaymentDAO {
    private final static Logger LOGGER = LogManager.getLogger(PaymentDAO.class);
    private static final String INSERT_QUERY = "INSERT INTO Payment (paymentId, userId, cardNumber, expirationDate) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE Payment SET userId=?, cardNumber=?, expirationDate=? WHERE paymentId=?";
    private static final String DELETE_QUERY = "DELETE FROM Payment WHERE paymentId=?";
    private static final String GET_QUERY = "SELECT * FROM Payment WHERE paymentId=?";

    @Override
    public void insert(Payment payment) {
        if (payment == null) {
            LOGGER.error("Payment object is null.");
            throw new IllegalArgumentException("Payment object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, payment.getPaymentId());
            preparedStatement.setInt(2, payment.getUserId());
            preparedStatement.setString(3, payment.getCardNumber());
            preparedStatement.setDate(4, payment.getExpirationDate());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public void update(Payment payment) {
        if (payment == null) {
            LOGGER.error("Payment object is null.");
            throw new IllegalArgumentException("Payment object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setInt(1, payment.getUserId());
            preparedStatement.setString(2, payment.getCardNumber());
            preparedStatement.setDate(3, payment.getExpirationDate());
            preparedStatement.setInt(4, payment.getPaymentId());
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
    public Payment getById(int id) {
        Payment payment = new Payment();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    payment.setPaymentId(resultSet.getInt("paymentId"));
                    payment.setUserId(resultSet.getInt("userId"));
                    payment.setCardNumber(resultSet.getString("cardNumber"));
                    payment.setExpirationDate(resultSet.getDate("expirationDate"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException("Unable to obtain resource.", e);
        }
        return payment;
    }
}