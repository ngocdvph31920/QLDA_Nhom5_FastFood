package poly.duan.fastfoodie.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import poly.duan.fastfoodie.Fragment.ConfirmFragment;
import poly.duan.fastfoodie.Fragment.DeliveriedFragment;
import poly.duan.fastfoodie.Fragment.DeliveringFragment;
import poly.duan.fastfoodie.Fragment.PreparingFragment;

public class OrderAdapter extends FragmentPagerAdapter {

    public OrderAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ConfirmFragment();
            case 1:
                return new PreparingFragment();
            case 2:
                return new DeliveringFragment();
            case 3:
                return new DeliveriedFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Chờ xác nhận";
            case 1:
                return "Đang chuẩn bị";
            case 2:
                return "Đang giao";
            case 3:
                return "Đã giao";
            default:
                return null;
        }
    }
}
