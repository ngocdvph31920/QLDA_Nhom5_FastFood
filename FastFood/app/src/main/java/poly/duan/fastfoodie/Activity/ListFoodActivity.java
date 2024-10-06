package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import poly.duan.fastfoodie.Adapter.ListFoodAdapter;
import poly.duan.fastfoodie.Model.Category;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ProductService;
import poly.duan.fastfoodie.databinding.ActivityListFoodBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFoodActivity extends AppCompatActivity {
    ActivityListFoodBinding binding;
    private String id, category;
        private String searchText;
    private boolean isSearch;

    ListFoodAdapter listFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Product product = (Product) getIntent().getSerializableExtra("productId");
        if (product != null) {
            // Nếu sản phẩm đã được chọn, chuyển đến DetailActivity
            Intent intent = new Intent(ListFoodActivity.this, DetailActivity.class);
            intent.putExtra("productId", product);
            startActivity(intent);
        } else {
            // Nếu không, gọi API để lấy danh sách sản phẩm và hiển thị chúng
            Log.d("Lỗi xem chi tiết", "lỗi ở đây này" + product);
        }
//        searchFood();
        getListFoodAPI();
        getIntExtra();

    }

    private void getListFoodAPI() {
        Intent intent = getIntent();
        String category = intent.getStringExtra("CategoryName");
        Log.d("Cate", "CategoryName: " + category);


        Category cat = new Category();
        cat.setCategory(category);
        ProductService.api.getListCat(cat).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {

                    List<Product> productList = response.body();
                    Log.d("List Lỗi", "Category: " + productList);

                    for (Product pro : productList) {
                        Log.d("Product", "FFFFF :" + pro.getId() + pro.getProductname() + pro.getDescription() + pro.getPrice() + pro.getImageproduct());
                    }
//                    Toast.makeText(ListFoodActivity.this, "Call API THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    listFoodAdapter = new ListFoodAdapter(productList);
                    binding.recyeListfood.setAdapter(listFoodAdapter);
                    binding.recyeListfood.setLayoutManager(new GridLayoutManager(ListFoodActivity.this, 2));

                } else {
                    Log.e("API_CALL_FAILURE1", "Không thể nhận dữ liệu sản phẩm từ API: " + response.message());
                    Toast.makeText(ListFoodActivity.this, "Không thể nhận dữ liệu sản phẩm từ API: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_CALL_FAILURE", "Thất bại thật rồi: " + t.getMessage());
                Toast.makeText(ListFoodActivity.this, "Call Api thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getIntExtra() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("CategoryID") && intent.hasExtra("CategoryName")) {

            id = intent.getStringExtra("CategoryID");
            category = intent.getStringExtra("CategoryName");

            searchText = intent.getStringExtra("text");
            isSearch = intent.getBooleanExtra("isSearch", false);

            Log.d("IntentData", "CategoryID: " + id);
//            Log.d("IntentData", "CategoryName: " + category);
            binding.titeleTxt.setText(category);
            binding.btnBack.setOnClickListener(v -> {
                finish();
            });
        } else {
            Log.e("IntentData", "Intent is null or does not contain necessary data");
        }
    }
}