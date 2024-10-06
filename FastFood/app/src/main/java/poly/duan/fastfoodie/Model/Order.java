package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("username")
    private String username;
    @SerializedName("total")
    private double total;
    @SerializedName("address")
    private String address;
    @SerializedName("payment_method")
    private String payment_method;
    @SerializedName("items")
    private List<ItemOrder> itemOrders;
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private String status;


    public Order() {
        this.username = username;
        this.total = total;
        this.address = address;
        this.payment_method = payment_method;
        this.itemOrders = itemOrders;
        this.msg = msg;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public List<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(List<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
