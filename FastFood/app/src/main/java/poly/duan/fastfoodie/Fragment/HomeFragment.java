package poly.duan.fastfoodie.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import poly.duan.fastfoodie.Activity.CartActivity;
import poly.duan.fastfoodie.Activity.IntroActivity;
import poly.duan.fastfoodie.Activity.ListFoodActivity;
import poly.duan.fastfoodie.Activity.SearchActivity;
import poly.duan.fastfoodie.Adapter.BestfoodAdapter;
import poly.duan.fastfoodie.Adapter.CategoryfoodAdapter;
import poly.duan.fastfoodie.Adapter.PhotoAdapter;
import poly.duan.fastfoodie.Model.Category;
import poly.duan.fastfoodie.Model.Photo;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ProductService;
import poly.duan.fastfoodie.databinding.FragmentHomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    PhotoAdapter adapter;
    BestfoodAdapter foodAdapter;
    CategoryfoodAdapter categoryfoodAdapter;

    private ArrayList<Photo> photoList;
    //    private ArrayList<Food> foodList;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Kiểm tra ảnh khi không còn ảnh nào để kiểm tra
            if (binding.viewPager.getCurrentItem() == photoList.size() - 1) {
                binding.viewPager.setCurrentItem(0); // Quay trở lại ảnh ban đầu
            } else {
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1); // Chuyển sang 1 ảnh tiếp theo
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);


        SharedPreferences sharedPreferences_name = getActivity().getSharedPreferences("myPre", MODE_PRIVATE);
        String userName = sharedPreferences_name.getString("userName", ""); // Lấy tên người dùng từ SharedPreferences

        // Gán tên người dùng vào TextView hoặc phần tử giao diện tương ứng
        binding.txtNameClient.setText(userName);

        photoList = new ArrayList<>();
        photoList.add(new Photo(R.drawable.piza));
        photoList.add(new Photo(R.drawable.burger));
        photoList.add(new Photo(R.drawable.hotdog));
        photoList.add(new Photo(R.drawable.kimchi));

        adapter = new PhotoAdapter(getContext(), photoList);
        binding.viewPager.setAdapter(adapter);
        binding.circleCenter.setViewPager(binding.viewPager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL);
//        binding.recyclerViewBestfood.addItemDecoration(dividerItemDecoration);
//        foodList = new ArrayList<>();

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
        getBestfood();
        getCategory();
        getSearchfood();

        binding.logOut.setOnClickListener(v -> {
            getDialogOut();
        });

        binding.cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), CartActivity.class));
        });
        return binding.getRoot();
    }

    private void getSearchfood() {
        binding.btnSearch.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SearchActivity.class));
        });
    }

    private void getBestfood() {
        ProductService.api.getSanPham().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> list_product = response.body();
                    for (Product product : list_product) {
                        Log.d("Product", "ProductName" + product.getId() + product.getProductname() + product.getPrice());
                    }
//                    Toast.makeText(getContext(), "Call API thành công", Toast.LENGTH_SHORT).show();
                    foodAdapter = new BestfoodAdapter(list_product);
                    binding.recyclerViewBestfood.setAdapter(foodAdapter);
                    binding.recyclerViewBestfood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                } else {
                    Log.e("API_CALL_FAILURE1", "Không thể nhận dữ liệu sản phẩm từ API: " + response.message());
                    Toast.makeText(getContext(), "Không thể nhận dữ liệu sản phẩm từ API: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_CALL_FAILURE", "Thất bại thật rồi: " + t.getMessage());
                Toast.makeText(getContext(), "Call Api thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCategory() {
        ProductService.api.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    List<Category> list = response.body();
                    for (Category cat : list) {
                        Log.d("Category", "CCCC :" + cat.getId() + cat.getCategory() + cat.getImageCat());
                    }
//                    Toast.makeText(getContext(), "Call API THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    categoryfoodAdapter = new CategoryfoodAdapter((ArrayList<Category>) list);
                    binding.recyclerViewCategory.setAdapter(categoryfoodAdapter);
                    binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(), 4));

                } else {
                    Log.e("API_CALL_FAILURE1", "Không thể nhận dữ liệu sản phẩm từ API: " + response.message());
                    Toast.makeText(getContext(), "Không thể nhận dữ liệu sản phẩm từ API: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("API_CALL_FAILURE", "Thất bại thật rồi: " + t.getMessage());
                Toast.makeText(getContext(), "Call Api thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getDialogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Notification");
        builder.setMessage("Do you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LogOut();
                Toast.makeText(getContext(), "Signed out successfully", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void LogOut() {
        startActivity(new Intent(getContext(), IntroActivity.class));
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }
}