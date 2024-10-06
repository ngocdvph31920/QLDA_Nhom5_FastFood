package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("username")
    private String username;
    @SerializedName("productname")
    private String productname;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("price")
    private double price;
    @SerializedName("total")
    private double total;
    @SerializedName("status")
    private String status;

    public OrderDetail() {
        this.username = username;
        this.productname = productname;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
