package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.dao_interface.PaymentDao;
import com.solvd.online.store.processing.Payment;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    List<Payment> payments;

    public PaymentDaoImpl(){
        payments = new ArrayList<Payment>();
        Payment payment1 = new Payment(1, 1, "1234567812345678", new Date(System.currentTimeMillis()));
        Payment payment2 = new Payment(2, 2, "2345678923456789", new Date(System.currentTimeMillis()));
        payments.add(payment1);
        payments.add(payment2);
    }

    @Override
    public void deletePayment(Payment payment) {
        payments.remove(payment.getPaymentId());
        System.out.println("Payment: Payment Id " + payment.getPaymentId() + ", deleted from database");
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public Payment getPayment(int paymentId) {
        return payments.get(paymentId);
    }

    @Override
    public void updatePayment(Payment payment) {
        payments.get(payment.getPaymentId()).setCardNumber(payment.getCardNumber());
        System.out.println("Payment: Payment Id " + payment.getPaymentId() + ", updated in the database");
    }
}
