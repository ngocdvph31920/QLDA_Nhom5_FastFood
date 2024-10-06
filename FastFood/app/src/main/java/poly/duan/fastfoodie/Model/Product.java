package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("_id")
    private String id;
    @SerializedName("productname")

    private String productname;
    @SerializedName("description")

    private String description;
    @SerializedName("price")

    private int price;
    @SerializedName("imageproduct")

    private String imageproduct;


    public Product() {
    }

    public Product(String id, String productname, String description, int price, String imageproduct) {
        this.id = id;
        this.productname = productname;
        this.description = description;
        this.price = price;
        this.imageproduct = imageproduct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageproduct() {
        return imageproduct;
    }

    public void setImageproduct(String imageproduct) {
        this.imageproduct = imageproduct;
    }
}
