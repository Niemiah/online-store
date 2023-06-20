package com.solvd.online.store.service.mybatisimpl;

import com.solvd.online.store.dao.IPaymentDAO;
import com.solvd.online.store.model.Payment;
import com.solvd.online.store.service.IPaymentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentService implements IPaymentService {
    private SqlSessionFactory sqlSessionFactory;
    private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);

    public PaymentService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void savePaymentToDB(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment object is null.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            paymentDAO.insert(payment);
            session.commit();
            LOGGER.info("Payment has been successfully saved to the database.");
        }
    }

    @Override
    public void updatePaymentInDB(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment object is null.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            paymentDAO.update(payment);
            session.commit();
            LOGGER.info("Payment has been successfully updated in the database.");
        }
    }

    @Override
    public Payment getPaymentInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            Payment payment = paymentDAO.getById(id);
            LOGGER.info("Retrieved payment from the database with id: " + id);
            return payment;
        }
    }

    @Override
    public void deletePaymentFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            paymentDAO.deleteById(id);
            session.commit();
            LOGGER.info("Payment with id: " + id + " has been deleted from the database.");
        }
    }
}