package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import poly.duan.fastfoodie.Adapter.FavoriteAdapter;
import poly.duan.fastfoodie.Adapter.ListFoodAdapter;
import poly.duan.fastfoodie.Model.Cart;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.Model.WithList;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ApiService;
import poly.duan.fastfoodie.Service.ProductService;
import poly.duan.fastfoodie.databinding.ActivityFavouriteBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteActivity extends AppCompatActivity {
    ActivityFavouriteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

        getWithList();


    }

    private void getWithList() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "-1");
        Log.d("ID nayf5555", "onResponse: "+userId);

        WithList withList = new WithList();
        withList.setUserId(userId);

       ApiService.api.getFavorite(withList).enqueue(new Callback<List<Product>>() {
           @Override
           public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
               if (response.isSuccessful()){
                   List<Product> productList = response.body();
                   for (Product pro: productList) {
                       Log.d("WithList", "ccccc :" + pro.getId() + pro.getProductname() + pro.getDescription() + pro.getPrice() + pro.getImageproduct());

                   }
                   FavoriteAdapter adapter = new FavoriteAdapter(productList);
                   binding.recyeFavorite.setAdapter(adapter);
                   binding.recyeFavorite.setLayoutManager(new LinearLayoutManager(FavouriteActivity.this,RecyclerView.VERTICAL,false));
                   Toast.makeText(FavouriteActivity.this, "Hiển thị thành công", Toast.LENGTH_SHORT).show();

               } else {
                   String errorBody = response.errorBody().toString();
                   Log.d("ERRO","E :"+errorBody);
                   Toast.makeText(FavouriteActivity.this, "Hiển thị thất bại"+response.errorBody().toString(), Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<List<Product>> call, Throwable t) {
               Toast.makeText(FavouriteActivity.this, "Lỗi"+t.getMessage(), Toast.LENGTH_SHORT).show();

           }
       });
        }

    }
