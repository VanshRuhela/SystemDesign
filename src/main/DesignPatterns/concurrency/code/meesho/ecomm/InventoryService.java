package concurrency.code.meesho.ecomm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InventoryService {
    private Map<String , Product> productMap; // productId : product in the inventory
    private Map<String, Order> orderMap; // orderId : order

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public InventoryService(){
        productMap = new HashMap<>();
        orderMap = new HashMap<>();
    }


    // 1. Create Product
    public void createProduct(String productId , String name , int count){
        if(productMap.containsKey(productId)){
            System.out.println("Product key already exists");
            return;
        }
        productMap.put(productId, new Product(productId, name, count));
    }

    // 2. get inventory by product id
    public int getInventory(String productId){
        Product product = productMap.get(productId);
        if(product == null){
            System.out.println("Product doesn't exists");
            return -1;
        }
        return product.getInventoryCount();
    }

    //3. createOrder
    public void createOrder(List<String> productIds, List<Integer> quantityOrdered , String orderId){
        if(orderMap.containsKey(orderId)){
            System.out.println("Order Already Exists");
        }

        Map<String , Integer> productOrderMap = new HashMap<>();
        for(int i =0; i<productIds.size(); i++){
            String productId = productIds.get(i);
            int quantity = quantityOrdered.get(i);
            Product product = productMap.get(productId);
            if(product == null){
                System.out.println("Product " + productId + "Not found");
            }
            else{
                if(product.getInventoryCount() < quantity) {
                    System.out.println("Insufficient Inventory Cant full fill");
//                    productOrderMap.put(productId, product.getInventoryCount());
//                    product.setInventoryCount(0);
                }
                else{
                    product.setInventoryCount(product.getInventoryCount() - quantity);
                    productOrderMap.put(productId, quantity);
                }
            }
            System.out.println(productOrderMap.entrySet().toString());
        }

        Order order = new Order(orderId, false ,productOrderMap);
        orderMap.put(orderId, order);
        scheduler.schedule( ()-> confirmOrder(orderId), 3000 , TimeUnit.MILLISECONDS);
    }


    //5 payment
    public void makePayment(String orderId){
        Order order = orderMap.get(orderId);
        if(order == null){
            System.out.println("No order exits");
        }
        else {
            order.setPaymentDone(true);
        }
    }
    //4.
    public void confirmOrder(String orderId){
        Order order = orderMap.get(orderId);
        if(order == null){
            System.out.println("Order Not found");
        }else{
            if(order.isPaymentDone()){
                System.out.println("Payment Done Order Cnf");
            }
            else {
                System.out.println("Payment Not Done");
                var orderQuanityMap = order.getProductOrderMap();
                for (var entry : order.getProductOrderMap().entrySet()) {
                    String productId = entry.getKey();
                    Product product = productMap.get(productId);
                    int qty = entry.getValue();
                    System.out.println("entry : " + entry.toString());
                    product.setInventoryCount(product.getInventoryCount() + qty);
                }
                System.out.println("Inventory resoterd");
                orderMap.remove(orderId);
                System.out.println("removed the order Id " + orderId);
            }
        }
    }
}
