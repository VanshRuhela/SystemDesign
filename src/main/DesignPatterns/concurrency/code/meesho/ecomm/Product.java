package concurrency.code.meesho.ecomm;

public class Product {
    private String productId;
    private String name;
    private int inventoryCount;

    public Product(String productId, String name, int inventoryCount) {
        this.productId = productId;
        this.name = name;
        this.inventoryCount = inventoryCount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
