    package poly.duan.fastfoodie.Fragment;

    import android.os.Bundle;

    import androidx.fragment.app.Fragment;
    import androidx.viewpager.widget.ViewPager;
    import androidx.viewpager2.widget.ViewPager2;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import com.google.android.material.tabs.TabLayout;

    import poly.duan.fastfoodie.Adapter.OrderAdapter;
    import poly.duan.fastfoodie.R;


    public class OrderFragment extends Fragment {
        private TabLayout tabLayout;
        private ViewPager viewPager;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_order,container,false);
            tabLayout = view.findViewById(R.id.tab_layout);
            viewPager = view.findViewById(R.id.view_pager);

            OrderAdapter adapter = new OrderAdapter(getChildFragmentManager());
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);

    //        tabLayout.addTab(tabLayout.newTab().setText("Chờ xác nhận"));
    //        tabLayout.addTab(tabLayout.newTab().setText("Đang chuẩn bị"));
    //        tabLayout.addTab(tabLayout.newTab().setText("Đang giao"));
    //        tabLayout.addTab(tabLayout.newTab().setText("Đã giao"));

            return view;
        }
    }