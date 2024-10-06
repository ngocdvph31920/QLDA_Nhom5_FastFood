package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

public class ItemOrder {
    @SerializedName("productname")
    private String productname;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("price")
    private double price;

    public ItemOrder() {
        this.productname = productname;
        this.quantity = quantity;
        this.price = price;
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
}
