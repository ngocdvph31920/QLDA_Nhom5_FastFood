package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import poly.duan.fastfoodie.Adapter.SearchAdapter;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ProductService;
import poly.duan.fastfoodie.databinding.ActivitySearchBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;
    SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.btnSearch.setOnClickListener(v -> {
            String edt_search = binding.edtSearchfood.getText().toString();
            if (!edt_search.isEmpty()) {
                Product product = new Product();
                product.setProductname(edt_search);
                ProductService.api.getFindProduct(product).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.isSuccessful()) {
                            List<Product> productList = response.body();
                            // Xử lý danh sách sản phẩm tìm được
                            // Ví dụ: Hiển thị danh sách sản phẩm tìm được bằng cách chuyển qua một Activity khác
//                            Intent intent = new Intent(SearchActivity.this, ListFoodActivity.class);
                            Log.d("Search", "Dữ liệu này" + productList);
//                            intent.putExtra("text",edt_search);
//                            intent.putExtra("isSearch",true);
                            searchAdapter = new SearchAdapter(productList);
                            binding.recyeListfood.setAdapter(searchAdapter);
                            binding.recyeListfood.setLayoutManager(new GridLayoutManager(SearchActivity.this,2));
                            Toast.makeText(SearchActivity.this, "Search Thành Công ", Toast.LENGTH_SHORT).show();

//                            startActivity(intent);
                        } else {
                            Log.e("API_CALL_FAILURE", "Không thể nhận dữ liệu sản phẩm từ API: " + response.message());
                            Toast.makeText(SearchActivity.this, "Không thể nhận dữ liệu sản phẩm từ API: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Log.e("API_CALL_FAILURE", "Lỗi khi gọi API: " + t.getMessage());
                        Toast.makeText(SearchActivity.this, "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Log.d("Search Rỗng", "Không thấy dữ liệu" + edt_search);
            }
        });
    }
}