package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WithList implements Serializable {
    @SerializedName("userId")
    private String userId;
    @SerializedName("productId")
    private String productId;

    public WithList(){}

    public WithList(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
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
}
