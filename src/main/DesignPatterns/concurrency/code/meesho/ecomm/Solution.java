package concurrency.code.meesho.ecomm;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        InventoryService inventoryService = new InventoryService();
        inventoryService.createProduct("P01", "Laptop", 10);
        inventoryService.createProduct("P02", "Phone", 20);
        inventoryService.createProduct("P03", "Mouse", 15);

        // available ?
        System.out.println("Inventory");
        System.out.println("Laptop " + inventoryService.getInventory("P01"));
        System.out.println("Phone " + inventoryService.getInventory("P02"));
        System.out.println("Mouse " + inventoryService.getInventory("P03"));

        List<String> productIds = Arrays.asList("P01", "P02");
        List<Integer> quanityList = Arrays.asList(5, 30);

        inventoryService.createOrder(productIds, quanityList, "order-1");

        List<String> productIds2 = Arrays.asList("P01", "P02", "P03");
        List<Integer> quanityList2 = Arrays.asList(5, 30, 10);
        inventoryService.createOrder(productIds2, quanityList2 , "order-2");

        System.out.println("Inventory");
        System.out.println("Laptop " + inventoryService.getInventory("P01"));
        System.out.println("Phone " + inventoryService.getInventory("P02"));
        System.out.println("Mouse " + inventoryService.getInventory("P03"));

        inventoryService.makePayment("order-1");
        inventoryService.makePayment("order-2");
        Thread.sleep(4000);
//        inventoryService.confirmOrder("order-1");
        System.out.println("Inventory");
        System.out.println("Laptop " + inventoryService.getInventory("P01"));
        System.out.println("Phone " + inventoryService.getInventory("P02"));
        System.out.println("Mouse " + inventoryService.getInventory("P03"));

    }
}
