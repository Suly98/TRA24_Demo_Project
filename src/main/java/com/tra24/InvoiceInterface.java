package com.tra24;
import java.util.List;

interface InvoiceInterface {
    public void printInvoice();
    public void setItems(List<Item> items);
    public void setShopName(String shopName);
    public void setCustomer(Customer customer);
    public void setDate(String date);
    public void setId();
    public Double getBalance();

}
