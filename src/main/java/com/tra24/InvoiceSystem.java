package com.tra24;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


@RestController
public class InvoiceSystem {

    public static List<Item> items = new ArrayList<>();
    public static HashMap<Integer, Invoice> invoiceHashMap = new HashMap<>();
    public static Scanner sc = new Scanner(System.in);
    public static Integer shopSettingsCounter = 0;
    public static Integer menuCounter = 0;
    public static Integer shopItemsCounter = 0;



    @GetMapping("run")
    public static void main(String[] args) {
        try {
            Integer option = 0;
            do {

                //FIXME: Surround with try/catch -- Done
                //TODO: Make it fail-safe -- Done
                menuCounter++;
                System.out.println(MenuConstants.mainMenu);
                System.out.print("Enter your option: ");
                option = sc.nextInt();
                if (option == 1) {
                    shopSettings();
                } else if (option == 2) {
                    manageShopItems();
                } else if (option == 3) {
                    createNewInvoice();
                } else if (option == 4) {
                    reportStatistics();
                } else if (option == 5) {
                    reportAllInvoices();
                } else if (option == 6) {
                    searchInvoices();
                } else if (option == 7) {
                    programStatistics();
                }
            } while (option != 8);
        } catch (Exception e) {
            System.out.println("You Entered a wrong input!");
        }
    }

    public static void programStatistics() {
        System.out.println(menuCounter + " Visited Main Menu");
        System.out.println(shopSettingsCounter + " Visited Shop Settings");
        System.out.println(shopItemsCounter + " Visited Manage Shop Items");
    }

    public static void searchInvoices() {
        System.out.println("Enter the invoice number that you want to search: ");
        Integer invoiceNo = sc.nextInt();
        Invoice invoiceToSearch = invoiceHashMap.get(invoiceNo);
        invoiceToSearch.printInvoice();
    }


    public static void createNewInvoice() {

        sc.nextLine();

        //FIXME: Creating customer should be in separate place
//        MainMenu.items.clear();
        Customer customer = new Customer();
        System.out.println("Enter the invoice date (YY-MM-DD): ");
        String date = sc.nextLine();
        System.out.println("Enter the Customer name: ");
        customer.setFullName(sc.nextLine());
        System.out.println("What is the amount Paid: ");
        Double amountPaid = sc.nextDouble();


        //Creating an Invoice
        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setHeader(setInvoiceHeader());
        invoice.setDate(date);
        invoice.setPaidAmount(amountPaid);
        invoice.setShopName(setShopName());

        //adding items to the invoice
        //Setting an id for each item in the invoice
        List<Item> items = addItems();
        int itemNo = 1;
        for (Item item : items) {
            item.setId(itemNo++);
        }
        invoice.setItems(items);


        invoice.setTotalAmount(Invoice.calculateTotalAmount(invoice.getItems()));

        //setting an id n adding invoice to the invoice map:
        invoice.setId();
        invoiceHashMap.put(invoice.getId(), invoice);
        //to print the invoice
        invoice.printInvoice();
    }


    public static void reportStatistics() {
        sc.nextLine();
        System.out.println("         Statistics:    ");
        System.out.println("No of Items:  " + items.size());
        System.out.println("No of Invoices: " + invoiceHashMap.size());
        System.out.println("Total of Sales: " + totalSales());
    }

    public static Double totalSales() {
        Double totalSales = 0.0;
        for (Invoice invoice : invoiceHashMap.values()) {
            totalSales += invoice.getTotalAmount();
        }

        return totalSales;
    }


    public static void reportAllInvoices() {
        System.out.println("    All Invoices:   ");
        for (Invoice invoice : invoiceHashMap.values()) {
            invoice.printInvoice();
        }
    }


    /************ SHOP SETTINGS *************/
    public static void shopSettings() {
        int option; //FIXME: Rename -- done
        try {
            do {

                //TODO: Change to constants class for menu as per requirements -- fixed
                shopSettingsCounter++;
                System.out.println(MenuConstants.shopMenu);
                System.out.print("Enter your option: ");
                option = sc.nextInt();
                if (option == 1) {
                    loadData();
                    sc.nextLine();
                } else if (option == 2) {
                    //FIXME: Update ShopName in User's provided invoice
                    setShopName();
                    sc.nextLine();
                } else if (option == 3) {
                    setInvoiceHeader();
                }
            } while (option != 4);
        } catch (Exception e){
            System.out.println("You Entered a wrong input!");
        }
    }

    public static void loadData() { //FIXME: Load data is not loading data
        for (Invoice invoice : invoiceHashMap.values()) {
            System.out.println(invoice.items);
        }
    }

    public static String setShopName() {
        System.out.println("Enter the shop name: ");
        String shopName = sc.nextLine();
        return shopName;
    }

    public static String setInvoiceHeader() {
        sc.nextLine();
        System.out.println("Enter The invoice header(Phone/Email/Fax/Website): ");
        String invoiceHeader = sc.nextLine();


        return invoiceHeader;
    }


    public static void manageShopItems() {
        int word;
        do {
            shopItemsCounter++;
            System.out.println("        Manage Shop Items:      ");
            System.out.println("1- Add items ");
            System.out.println("2- Delete Items ");
            System.out.println("3- Change Item price");
            System.out.println("4- Report Item Price");
            System.out.println("5- Go Back ");
            System.out.print("Enter your option: ");
            word = sc.nextInt();
            if (word == 1) {
                sc.nextLine();

                InvoiceSystem.items = addItems();
            } else if (word == 2) {
                removeItem();
            } else if (word == 3) {
                changeItemPrice();
            } else if (word == 4) {
                reportItems();

            }
        } while (word != 5);
    }


    /***************** MANAGE SHOP ITEMS ************/
    public static List<Item> addItems() {

        List<Item> newItems = new ArrayList<>();

//        System.out.println("Enter the item number: ");
//        Integer id = sc.nextInt();

        Boolean cond = true;
        while (cond) {
            System.out.println("What is the item's name: ");
            String name = sc.nextLine();
            System.out.println("What is the item's price? ");
            Float price = sc.nextFloat();
            sc.nextLine();
            System.out.println("How many did you buy? ");
            Integer quantity = sc.nextInt();

            //issuing a new item from Item class
            Item item1 = new Item(name, price, quantity);

            //creating a new id for the item everytime I run this code
//            item1.setId();

            //adding item to MainMenu list items and printing the item
            newItems.add(item1);
            item1.print();

            //checking if item was added
            if (newItems.contains(item1)) {
                System.out.println("Items added Successfully!\n");
            } else {
                System.out.println("Items wasn't added!\n");
            }
            sc.nextLine();
            System.out.println("Do you want to add more items? y/n ");
            String word = sc.nextLine();
            if (word.equalsIgnoreCase("n")) {
                cond = false;
            }
        }
        return newItems;
    }

    public static void removeItem() {
        sc.nextLine();
        System.out.println("Enter the item name that you want to remove: ");
        String name = sc.nextLine();
        Item itemToRemove = new Item();

        //I tried items.remove but it didn't work because it is not addressed the same in the code
        // but removeif does the wonderful job

        items.removeIf(item -> item.getName().equals(name));

/*        for (Item i: items) {
            if(i.getName().equals(name)){
                itemToRemove = i;
            }
        }
        items.remove(itemToRemove);*/


        if (!items.contains(name)) {
            System.out.println("Item " + name + " was remove successfully!\n");
        } else {
            System.out.println("Items wasn't remove successfully!\n");
        }

    }

    public static void changeItemPrice() {
        sc.nextLine();

        System.out.println("Enter the item number that you want to change its price: ");
        Integer itemNo = sc.nextInt();

        System.out.println("Enter the new price: ");
        Float newPrice = sc.nextFloat();
        for (Item item : items) {
            if (item.id.equals(itemNo)) {
                item.setPrice(newPrice);
                System.out.println("Item price was changed successfully!\n");
            } else {
                System.out.println("Item not found!\n");
            }
        }

    }

    public static void reportItems() {
        for (Item item : items) {
            item.print();
        }

    }


}

