package poly.duan.fastfoodie.Activity;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import poly.duan.fastfoodie.Model.Address;
import poly.duan.fastfoodie.Model.ItemOrder;
import poly.duan.fastfoodie.Model.Order;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ApiService;
import poly.duan.fastfoodie.Service.OrderService;
import poly.duan.fastfoodie.databinding.ActivityBuyBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyActivity extends AppCompatActivity {

    ActivityBuyBinding binding;
    ArrayAdapter<String> spinnerAdapter;
    String[] paymentMethod = {"Tiền mặt", "Thẻ tín dụng", "MoMo"};
    private String selectedAddress;
    private String methodPay;
//    List<Address> addressList = new ArrayList<>(); // Thêm danh sách địa chỉ


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Product product = (Product) getIntent().getSerializableExtra("productId");
//        binding.txtNameOrder.setText(product.getProductname());
////        binding.txtQuantityOrder.setText(product.get());

        binding.btnBuyNow.setOnClickListener(v -> {
            buyNow();
        });
        binding.btnHuyBuy.setOnClickListener(v -> {
            finish();
        });
        getAddressUser();
        Intent intent = getIntent();
        if (intent != null) {
            Product product = (Product) intent.getSerializableExtra("productId");
            int quantity = intent.getIntExtra("quantity", 0);
            double price = intent.getIntExtra("price", 0);

            String total = getIntent().getStringExtra("total");
            String productname = String.valueOf(intent.getIntExtra("productname", 0));


            // Sau khi nhận được dữ liệu, bạn có thể hiển thị nó lên TextView hoặc bất kỳ thành phần nào khác
            // Ví dụ:
            binding.txtNameOrder.setText(product.getProductname());
            binding.txtQuantityOrder.setText(String.valueOf(quantity));
            binding.txtPriceOrder.setText(String.valueOf(price));
            binding.total.setText(String.valueOf(total));
        }

        SharedPreferences sharedPreferences_name = this.getSharedPreferences("myPre", MODE_PRIVATE);
        String userName = sharedPreferences_name.getString("userName", ""); // Lấy tên người dùng từ SharedPreferences
        binding.txtNameUser.setText(userName);

        // Lấy số điện thoại từ SharedPreferences
        SharedPreferences sharedPreferencesPhone = getSharedPreferences("myPre", MODE_PRIVATE);
        String phone = sharedPreferencesPhone.getString("phone", "");
        Log.d("Số điện thoại", "Số điện thoại lấy từ SharedPreferences: " + phone);
        binding.txtPhoneUser.setText(String.valueOf(phone));


        spinnerAdapter = new ArrayAdapter<>(this, R.layout.sp_item, paymentMethod);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPayment.setAdapter(spinnerAdapter);


        binding.spinnerPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ///xử lí click chọn thanh toán
                methodPay = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ///xử lí click chọn địa chỉ
                selectedAddress = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnAddtoAddress.setOnClickListener(v -> {
            addToAddress();
        });

    }

    private void getAddressUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "-1");
        Log.d("ID nè", "ở Address " + userId);
        Address address = new Address();
        address.setUserId(userId);

        ApiService.api.getAddress(address).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if (response.isSuccessful()) {

                    Address a = response.body();
                    Log.d("Address", "onResponse: " + a.getAddress());
//                    List<String> addressList = a.getAddress(); // tạo list để gán dữ liệu lên adapter
                    List<String> addressList = new ArrayList<>();
                    for (String address : a.getAddress()) {
                        if (address != null) {
                            addressList.add(address);
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(BuyActivity.this, R.layout.sp_item, addressList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerLocation.setAdapter(adapter);
//                    Toast.makeText(BuyActivity.this, "OK", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(BuyActivity.this, "OK" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    Log.d("ERROR222222", "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Toast.makeText(BuyActivity.this, "OK" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERROR33333", "onResponse: " + t.getMessage());
            }
        });
    }

    private void addToAddress() {
        TextInputEditText ed_address;
        Button btn_add_address;

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.bottomsheet_buy, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BuyActivity.this);
        bottomSheetDialog.setContentView(view);

        ed_address = view.findViewById(R.id.edt_diachi);
        btn_add_address = view.findViewById(R.id.btn_address);
        btn_add_address.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
            String userId = sharedPreferences.getString("userId", "-1");
            String address = ed_address.getText().toString();
            finish();

            Log.d("TAG", "addToAddress: " + userId);

            ApiService.api.addAddress(userId, address).enqueue(new Callback<Address>() {
                @Override
                public void onResponse(Call<Address> call, Response<Address> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(BuyActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        getAddressUser();
                        bottomSheetDialog.dismiss(); // Tắt BottomSheetDialog sau khi thêm thành công
                    } else {
                        Toast.makeText(BuyActivity.this, "Lỗi" + response.errorBody(), Toast.LENGTH_SHORT).show();
                        Log.d("e", "onResponse: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<Address> call, Throwable t) {
                    Log.d("TAG", "onFailure: " + t.toString());
                    getAddressUser();
                    bottomSheetDialog.dismiss();
                }
            });
        });

        bottomSheetDialog.show();

    }

    private void buyNow() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "-1");
        SharedPreferences sharedPreferences_name = this.getSharedPreferences("myPre", MODE_PRIVATE);
        String userName = sharedPreferences_name.getString("userName", "");
        Intent intent = getIntent();

        Product product = (Product) intent.getSerializableExtra("productId");
        int quantity = intent.getIntExtra("quantity", 0);
        Log.d("quantity", "buyNow: " +quantity);
        double price = intent.getIntExtra("price", 0);
        Log.d("price", "buyNow: " +price);
        String total = getIntent().getStringExtra("total");
        Log.d("total", "buyNow: " +total);
        String productname = product.getProductname();
        Log.d("productname", "buyNow: " +productname);
        Log.d("address", "buyNow: " +selectedAddress);

        List<ItemOrder> itemOrders = new ArrayList<>();
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setProductname(productname);
        itemOrder.setQuantity(quantity);
        itemOrder.setPrice(price);
        itemOrders.add(itemOrder);
        //
        Order order = new Order();
        order.setUsername(userName);
        order.setTotal(Double.parseDouble(total));
        order.setAddress(selectedAddress);
        order.setItemOrders(itemOrders);
        order.setPayment_method(methodPay);

        OrderService.api.addOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()){
                    Order orderRes = response.body();
                    String msg = orderRes.getMsg();

                    showOrderSuccessDialog();

                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });

    }
    private void showOrderSuccessDialog() {
        // Hiển thị dialog thông báo đặt hàng thành công
        AlertDialog.Builder builder = new AlertDialog.Builder(BuyActivity.this);
        builder.setTitle("Đặt hàng thành công");
        builder.setMessage("Cảm ơn bạn đã đặt hàng!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng dialog
                dialog.dismiss();
                // Chuyển đến hoạt động hoặc màn hình khác tùy theo nhu cầu của bạn
                startActivity(new Intent(BuyActivity.this, MainActivity.class));

            }
        });
        builder.setCancelable(false); // Không cho phép hủy dialog bằng cách nhấn nút back
        builder.show();
    }

}