package poly.duan.fastfoodie.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import poly.duan.fastfoodie.Fragment.HomeFragment;
import poly.duan.fastfoodie.Fragment.NotificationFragment;
import poly.duan.fastfoodie.Fragment.OrderFragment;
import poly.duan.fastfoodie.Fragment.ProfileFragment;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.btn_home) {
                    replaceFragment(new HomeFragment(),false);
                } else if (itemId == R.id.btn_order) {
                    replaceFragment(new OrderFragment(),false);
                } else if (itemId == R.id.btn_notification) {
                    replaceFragment(new NotificationFragment(),false);
                } else {
                    replaceFragment(new ProfileFragment(),false);
                }
                return true;
            }
        });
        replaceFragment(new HomeFragment(),false);
    }

    private void replaceFragment(Fragment fr, boolean isA) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isA) {
            fragmentTransaction.add(R.id.frame_layout, fr);

        } else {
            fragmentTransaction.replace(R.id.frame_layout, fr);

        }
        fragmentTransaction.commit();
    }
}