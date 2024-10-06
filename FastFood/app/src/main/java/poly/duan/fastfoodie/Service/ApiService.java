package poly.duan.fastfoodie.Service;

import java.util.List;

import poly.duan.fastfoodie.Model.Address;
import poly.duan.fastfoodie.Model.Cart;
import poly.duan.fastfoodie.Model.CartItem;
import poly.duan.fastfoodie.Model.CartResponse;
import poly.duan.fastfoodie.Model.Password;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.Model.User;
import poly.duan.fastfoodie.Model.WithList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    ApiService api = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

    @POST("api/register")
    Call<User> register(@Body User user);

    @POST("api/login")
    Call<User> login(@Body User user);

    @POST("addToWithList")
    Call<WithList> addToWithList(@Body WithList withList);

    @POST("addToCart")
    Call<CartResponse> addToCart(@Body Cart cart);

    @POST("getCart")
    Call<List<CartItem>> getCart(@Body Cart cart);

    @POST("getWithList")
    Call<List<Product>> getFavorite(@Body WithList withList);

    @POST("getAddress")
    Call<Address> getAddress(@Body Address address);

    @FormUrlEncoded
    @POST("addAddress")
    Call<Address> addAddress(
            @Field("userId") String userId,
            @Field("address") String address
    );

    @POST("updateCart")
    Call<CartResponse> updateCart(@Body Cart cart);
    @POST("ChangePass")
    Call<Password> changepass(@Body Password password);


}
