package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {
    @SerializedName("_id")

    private String id;
    @SerializedName("category")
    private String category;
    @SerializedName("imageCat")
    private String imageCat;

    public Category() {
    }

    public Category(String id, String category, String imageCat) {
        this.id = id;
        this.category = category;
        this.imageCat = imageCat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageCat() {
        return imageCat;
    }

    public void setImageCat(String imageCat) {
        this.imageCat = imageCat;
    }
}
