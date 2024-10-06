package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import poly.duan.fastfoodie.Model.Password;
import poly.duan.fastfoodie.Service.ApiService;
import poly.duan.fastfoodie.databinding.ActivityChangePasswordBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnDoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpassword =   binding.edtOldPass.getText().toString();
                String newpass =   binding.edtNewPass.getText().toString();
                String confimpass =  binding.edtRePass.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
                String email = sharedPreferences.getString("mail", "-1");
                Log.d("ngocdv", "zzzz  "+ email);

                Password password = new Password();
                password.setEmail(email);
                password.setPasword(oldpassword);
                password.setNewpassword(newpass);
                password.setConfirmpassword(confimpass);

                if (oldpassword.isEmpty()||newpass.isEmpty()||confimpass.isEmpty()){
                    Toast.makeText(ChangePasswordActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (newpass.length() <6 || confimpass.length()<6) {
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu phải lớn hơn 6 kí tự", Toast.LENGTH_SHORT).show();
                } else if (!newpass.equals(confimpass)) {
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu không trùng khớp ", Toast.LENGTH_SHORT).show();
                }

                ApiService.api.changepass(password).enqueue(new Callback<Password>() {
                    @Override
                    public void onResponse(Call<Password> call, Response<Password> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ChangePasswordActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));

                        }else{
                            Toast.makeText(ChangePasswordActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Password> call, Throwable t) {
                        Log.d("loi", "onFailure: "+t.getMessage());
                    }
                });
            }
        });
    }
}