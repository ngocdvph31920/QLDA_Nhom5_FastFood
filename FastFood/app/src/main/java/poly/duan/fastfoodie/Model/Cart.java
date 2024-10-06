package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cart implements Serializable {
    @SerializedName("userId")
    private String userId;
    @SerializedName("productId")

    private String productId;
    @SerializedName("price")

    private double price;

    @SerializedName("quantity")
    private int quantity;
    @SerializedName("total_order")
    private double total_order;

   public Cart(){}

    public Cart(String userId, String productId, double price,double total_order, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.total_order =total_order;
        this.quantity = quantity;
    }

    public String getUserId(String userId) {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal_order() {
        return total_order;
    }

    public void setTotal_order(double total_order) {
        this.total_order = total_order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
