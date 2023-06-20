package com.solvd.online.store.service.impl;

import com.solvd.online.store.dao.IPaymentDAO;
import com.solvd.online.store.dao.impl.PaymentDAO;
import com.solvd.online.store.service.IPaymentService;
import com.solvd.online.store.model.Payment;

public class PaymentService implements IPaymentService {

    @Override
    public void savePaymentToDB(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment object is null.");
        }

        IPaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.insert(payment);
    }

    @Override
    public void updatePaymentInDB(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment object is null.");
        }

        IPaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.update(payment);
    }

    @Override
    public Payment getPaymentInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IPaymentDAO paymentDAO = new PaymentDAO();
        return paymentDAO.getById(id);
    }

    @Override
    public void deletePaymentFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IPaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.deleteById(id);
    }
}