package com.solvd.online.store.dao_interface;
import com.solvd.online.store.processing.Payment;
import java.util.List;

public interface PaymentDao {
    public List<Payment> getAllPayments();
    public Payment getPayment(int paymentId);
    public void updatePayment(Payment payment);
    public void deletePayment(Payment payment);
}
