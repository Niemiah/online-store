package com.solvd.online.store.dao_interface;
import com.solvd.online.store.processing.Invoice;
import java.util.List;

public interface InvoiceDao {
    public List<Invoice> getAllInvoices();
    public Invoice getInvoice(int invoiceId);
    public void updateInvoice(Invoice invoice);
    public void deleteInvoice(Invoice invoice);
}
