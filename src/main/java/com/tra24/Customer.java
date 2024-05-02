package com.tra24;

import java.util.Objects;

public class Customer implements customerInterface {
    Integer id;
    String fullName;
    String phoneNo;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }




    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(fullName, customer.fullName)&& Objects.equals(phoneNo, customer.phoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, phoneNo);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + fullName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
