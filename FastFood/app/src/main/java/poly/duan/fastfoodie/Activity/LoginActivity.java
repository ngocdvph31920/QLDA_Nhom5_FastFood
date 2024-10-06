package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import poly.duan.fastfoodie.Model.User;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ApiService;
import poly.duan.fastfoodie.databinding.ActivityLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtSignup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
        binding.btnLogin.setOnClickListener(v -> {
            setLogin();
        });
    }

    private void setLogin() {
        String email = binding.edtEmail.getText().toString();
        String pass = binding.edtPassword.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(pass);

        ApiService.api.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user_data = null;
                if (response.isSuccessful()) {
                    user_data = response.body();
                    String role = user_data.getRole();
                    Log.d("role", "onResponse: " +role);
//                    if (user_data.getRole() == "admin") {
//                        Toast.makeText(LoginActivity.this, "Người dùng không có quyền truy cập", Toast.LENGTH_SHORT).show();
//                    }
                    Log.d("data : ", "data này :" + user_data);
                    if (user_data != null) {
                        String userId = user_data.getUserId();
                        String phone = String.valueOf(user_data.getPhone());

//                        String phone = String.valueOf(user_data.getPhone());
//                        Log.d("phone", "phone: "+phone);
                        Log.d("userId : ", "ID ở đây này :" + userId);

                        SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userId", userId);
                        editor.apply();

                        SharedPreferences sharedPreferencesPhone = getSharedPreferences("myPre", MODE_PRIVATE);
                        SharedPreferences.Editor editorPhone = sharedPreferencesPhone.edit();
                        editorPhone.putString("phone", phone);
                        editorPhone.apply();


                        String username = user_data.getUsername(); //hiển thị tênn client lên Trang chủ
                        SharedPreferences sharedPreferences_name = getSharedPreferences("myPre", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences_name.edit();
                        edit.putString("userName", username);
                        edit.apply();

                        String email = user_data.getEmail(); //hiển thị email client lên Trang chủ
                        SharedPreferences sharedPreferences_mail = getSharedPreferences("myPre", MODE_PRIVATE);
                        SharedPreferences.Editor edit_mail = sharedPreferences_mail.edit();
                        edit_mail.putString("mail", email);
                        edit_mail.apply();
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }  else {
                    Toast.makeText(LoginActivity.this, "Người dùng không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}