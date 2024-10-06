package poly.duan.fastfoodie.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import poly.duan.fastfoodie.Model.Category;
import poly.duan.fastfoodie.Model.Product;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ProductService api = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ProductService.class);

    @GET("getProduct")
    Call<List<Product>> getSanPham();

    @GET("getCat")
    Call<List<Category>> getCategory();

    @POST("getProByCat")
    Call<List<Product>> getListCat(@Body Category product);

    @POST("findProduct")
    Call<List<Product>> getFindProduct(@Body Product product);

}
