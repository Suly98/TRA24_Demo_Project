package com.tra24;


import java.util.List;
import java.util.Objects;

public class Invoice implements InvoiceInterface {

    //To auto add the invoices id and incrementing them one by one:
    public static Integer nextId = 1;

    //Attributes
    Integer id;
    String shopName;
    List<Item> items;
    String date;
    Customer customer;
    String header;
    Integer NoOfItems;
    Double totalAmount;
    Double paidAmount;
    Double balance;


    public static Integer generateId() {
        return nextId++;
    }

    // calculate the total amount
    public static double calculateTotalAmount(List<Item> items) {
        Double totalAmt = 0d;
        for (Item i : items) {
            totalAmt += i.getPrice() * i.getQuantity();
        }
        return totalAmt;
    }


    //to print invoices
    public void printInvoice() {
        System.out.println("Invoice No: " + id);
        System.out.println("Shop name:  " + shopName);
        System.out.println("Invoice header:  " + header);
        System.out.println("Invoice Date: " + date);
        System.out.println("Customer Name: " + customer.fullName);
        System.out.println("Items:" + getItems());
        System.out.println("Paid Amount: " + getPaidAmount());
        System.out.println("Total amount : " + getTotalAmount());
        System.out.println("The Balanced left: " + getBalance());
        System.out.println("\n");

    }


    public void loadDate(){
        System.out.println("Invoice No: " + id);
        System.out.println("Shop name:  " + shopName);
        System.out.println("Invoice header:  " + header);
        System.out.println("Invoice Date: " + date);
        System.out.println("Customer Name: " + customer.fullName);
//        System.out.println("Items:" + );
        System.out.println("Paid Amount: " + getPaidAmount());
        System.out.println("Total amount : " + getTotalAmount());
        System.out.println("The Balanced left: " + getBalance());
        System.out.println("\n");
    }



    public void addItems(Item item){
        items.add(item);
    }

    public List<Item> getItems() {
        for (Item item : items) {
            item.print();
        }
        return items;
    }



    public void setItems(List<Item> items) {
        this.items = items;
    }


    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getShopName() {
        return shopName;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Integer getId() {
        return id;
    }

    public void setId() {
        this.id = generateId();
    }

    public Integer getNoOfItems() {

        return NoOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        noOfItems += items.size();
        NoOfItems = noOfItems;
    }

    public Double getTotalAmount() {
        return Double.parseDouble(String.format("%.2f", totalAmount));
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getBalance() {
        if (getPaidAmount() >= getTotalAmount()) {
            balance = getPaidAmount() - getTotalAmount();

        } else if (getTotalAmount() > getPaidAmount()) {
            Double leftAmount = getTotalAmount() - getPaidAmount();
            System.out.println("You have " + leftAmount + "left to Pay!!");
            balance = 0-leftAmount;

        }
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(date, invoice.date) && Objects.equals(NoOfItems, invoice.NoOfItems) && Objects.equals(totalAmount, invoice.totalAmount) && Objects.equals(paidAmount, invoice.paidAmount) && Objects.equals(balance, invoice.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, NoOfItems, totalAmount, paidAmount, balance);

    }

    @Override
    public String toString() {
        return "Invoice{" +
                "item=" + items +
                ", date=" + date +
                ", customer=" + customer +
                ", header='" + header + '\'' +
                ", NoOfItems=" + NoOfItems +
                ", totalAmount=" + totalAmount +
                ", paidAmount=" + paidAmount +
                ", balance=" + balance +
                '}';
    }
}

