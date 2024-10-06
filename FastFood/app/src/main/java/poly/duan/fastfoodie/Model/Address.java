package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Address {
    @SerializedName("userId")

    private String userId;
    @SerializedName("address")

    private List<String> address;

    public Address() {
        this.userId = userId;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }
}
