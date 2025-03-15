package concurrency.code.meesho.ecomm;

import java.util.Map;

public class Order {
    private String orderId;
    private boolean isPaymentDone;

    private Map<String , Integer> productOrderMap; // product Order Map productId : orderedQuantity

    public Order(String orderId, boolean isPaymentDone, Map<String, Integer> productOrderMap) {
        this.orderId = orderId;
        this.isPaymentDone = isPaymentDone;
        this.productOrderMap = productOrderMap;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isPaymentDone() {
        return isPaymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        isPaymentDone = paymentDone;
    }

    public Map<String, Integer> getProductOrderMap() {
        return productOrderMap;
    }

    public void setProductOrderMap(Map<String, Integer> productOrderMap) {
        this.productOrderMap = productOrderMap;
    }
}
