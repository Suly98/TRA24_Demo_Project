package com.tra24;
import java.util.Objects;

public class Item implements itemInterface{
    public static int counter = 1;
    Integer id;
    String name;
    Float price;
    Integer quantity;


    public Item(String name, Float price, Integer quantity) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Item() {

    }


    /*-------------fUNCTIONS------------*/
    //to add items
/*    public void addToList(Item item) {
        items.add(item);
        System.out.println(items.toString());
    }*/



    //to print all items
    public void print() {
        System.out.println("");
        System.out.println("Item No: " + id);
        System.out.println("Item Name: " + name);
        System.out.printf("Price: %.2f%n", price);
        System.out.println("Quantity: " + quantity);
    }

    //to delete items
/*    public void removeItem(Item item) {
        items.remove(item);
    }*/

    //to change item price
    public void changeItemPrice(Item item, Float newPrice) {
        item.setPrice(newPrice);
    }




    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public static int calculateId() {
        return Item.counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(price, item.price) && Objects.equals(quantity, item.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

}
