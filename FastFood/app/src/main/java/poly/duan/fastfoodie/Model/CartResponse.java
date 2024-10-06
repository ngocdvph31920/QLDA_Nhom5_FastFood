package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartResponse {
    @SerializedName("cart")
    private List<CartItem> cartItemList;

    public CartResponse(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
