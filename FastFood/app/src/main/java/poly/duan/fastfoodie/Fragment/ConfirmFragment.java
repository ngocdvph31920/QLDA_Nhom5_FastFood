package poly.duan.fastfoodie.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import poly.duan.fastfoodie.Adapter.ConfirmAdapter;
import poly.duan.fastfoodie.Model.ItemOrder;
import poly.duan.fastfoodie.Model.Order;
import poly.duan.fastfoodie.Model.OrderDetail;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.OrderService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ConfirmFragment extends Fragment {
    private RecyclerView recycle_confirm;
    private ConfirmAdapter adapter;
    private List<OrderDetail> orderDetailList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);
        recycle_confirm = view.findViewById(R.id.recycle_confirm);

        orderDetailList = new ArrayList<>();
        adapter = new ConfirmAdapter(getContext(),orderDetailList);
        recycle_confirm.setLayoutManager(new LinearLayoutManager(getContext()));
        recycle_confirm.setAdapter(adapter);

        getOrderDetails();

        return view;
    }

    private void getOrderDetails() {
        // Lấy thông tin username từ SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPre", MODE_PRIVATE);
        String username = sharedPreferences.getString("userName", "");

        // Tạo đối tượng OrderDetail để gửi yêu cầu lấy danh sách đơn hàng
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setUsername(username);

        // Gọi API để lấy danh sách đơn hàng
        OrderService.api.getConfirm(orderDetail).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    List<Order> orderList = response.body();
                    for (Order order : orderList) {
                        double total = order.getTotal();
                        String status = order.getStatus();
                        List<ItemOrder> itemOrderList = order.getItemOrders();
                        for (ItemOrder items : itemOrderList) {
                            String productname = items.getProductname();
                            double price = items.getPrice();
                            int quantity = items.getQuantity();

                            // Tạo một đối tượng OrderDetail từ thông tin của mỗi ItemOrder
                            OrderDetail orderDetailItem = new OrderDetail();
                            orderDetailItem.setProductname(productname);
                            orderDetailItem.setQuantity(quantity);
                            orderDetailItem.setPrice(price);
                            orderDetailItem.setStatus(status);
                            orderDetailItem.setTotal(total);

                            // Thêm đối tượng OrderDetail vào danh sách
                            orderDetailList.add(orderDetailItem);
                            Log.d("list", "onResponse: "+orderDetailList);
                        }
                    }
                    // Cập nhật dữ liệu trên RecyclerView
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("ConfirmFragment", "Failed to get orders: " + response.message());
                    Toast.makeText(getContext(), "Failed to get orders", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e("ConfirmFragment", "Error: " + t.getMessage());
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
