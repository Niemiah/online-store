package com.solvd.online.store.service;
import com.solvd.online.store.model.Payment;

public interface IPaymentService {
    void savePaymentToDB(Payment payment);

    void updatePaymentInDB(Payment payment);

    Payment getPaymentInDB(int id);

    void deletePaymentFromDB(int id);

}