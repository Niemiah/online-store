package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IPaymentDAO;
import com.solvd.online.store.impl.PaymentDAO;
import com.solvd.online.store.service.IPaymentService;
import com.solvd.online.store.model.Payment;

public class PaymentService implements IPaymentService {

    @Override
    public void savePaymentToDB(Payment payment) {
        IPaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.insert(payment);
    }

    @Override
    public void updatePaymentInDB(Payment payment) {
        IPaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.update(payment);
    }

    @Override
    public Payment getPaymentInDB(int id) {
        IPaymentDAO paymentDAO = new PaymentDAO();
        return paymentDAO.getById(id);
    }

    @Override
    public void deletePaymentFromDB(int id) {

    }

    @Override
    public String getAllPaymentsFromDB() {
        return null;
    }
}