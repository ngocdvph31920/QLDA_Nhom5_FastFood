package poly.duan.fastfoodie.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import poly.duan.fastfoodie.Model.Order;
import poly.duan.fastfoodie.Model.OrderDetail;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setLenient().create();

    OrderService api =  new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrderService.class);

    @POST("api/addOrder")
    Call<Order> addOrder(@Body Order order);

    @POST("getConfirm")
    Call<List<Order>> getConfirm(@Body OrderDetail orderDetail);
    @POST("getgetPreparingGoods")
    Call<List<Order>> getPreparing(@Body OrderDetail orderDetail);
    @POST("getAreDelivering")
    Call<List<Order>> getDelivering(@Body OrderDetail orderDetail);
    @POST("getDelived")
    Call<List<Order>> getDelived(@Body OrderDetail orderDetail);
}
