package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }
    private void setVariable() {
        binding.textViewLogin.setOnClickListener(v -> {
            startActivity(new Intent(IntroActivity.this, LoginActivity.class));
        });

        binding.textViewSignUp.setOnClickListener(v -> {
            startActivity(new Intent(IntroActivity.this, SignUpActivity.class));

        });
    }
}