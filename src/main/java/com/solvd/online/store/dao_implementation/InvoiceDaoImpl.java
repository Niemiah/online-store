package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.processing.Invoice;
import com.solvd.online.store.dao_interface.InvoiceDao;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao {

    List<Invoice> invoices;

    public InvoiceDaoImpl(){
        invoices = new ArrayList<Invoice>();
        Invoice invoice1 = new Invoice(1, 1, 1, 1);
        Invoice invoice2 = new Invoice(2, 2, 2, 2);
        invoices.add(invoice1);
        invoices.add(invoice2);
    }

    @Override
    public void deleteInvoice(Invoice invoice) {
        invoices.remove(invoice.getInvoiceId());
        System.out.println("Invoice: Invoice Id " + invoice.getInvoiceId() + ", deleted from database");
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoices;
    }

    @Override
    public Invoice getInvoice(int invoiceId) {
        return invoices.get(invoiceId);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        invoices.get(invoice.getInvoiceId()).setOrderId(invoice.getOrderId());
        invoices.get(invoice.getInvoiceId()).setUserId(invoice.getUserId());
        invoices.get(invoice.getInvoiceId()).setPaymentId(invoice.getPaymentId());
        System.out.println("Invoice: Invoice Id " + invoice.getInvoiceId() + ", updated in the database");
    }
}