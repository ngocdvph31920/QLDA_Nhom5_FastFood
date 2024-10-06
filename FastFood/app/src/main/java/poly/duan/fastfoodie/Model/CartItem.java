package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartItem implements Serializable {
    private boolean isChecked;

    @SerializedName("productId")
    private String productId;
    @SerializedName("productname")
    private String productname;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("price")
    private double price;

    @SerializedName("total_order")
    private double total_order;

    public CartItem(){}

    public CartItem(String productId, String productname, int quantity, double price, double total_order) {
        this.productId = productId;
        this.productname = productname;
        this.quantity = quantity;
        this.price = price;
        this.total_order = total_order;
    }

    public CartItem(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public double getTotal_order() {
        return total_order;
    }

    public void setTotal_order(double total_order) {
        this.total_order = total_order;
    }
    // Định nghĩa các phương thức getter/setter nếu cần
}
