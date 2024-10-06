package poly.duan.fastfoodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import poly.duan.fastfoodie.Model.OrderDetail;
import poly.duan.fastfoodie.R;

public class DeliveringAdapter extends RecyclerView.Adapter<DeliveringAdapter.ViewHolder> {
    private Context context;
    private List<OrderDetail> orderList;

    public DeliveringAdapter(Context context, List<OrderDetail> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public DeliveringAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveringAdapter.ViewHolder holder, int position) {
        OrderDetail order = orderList.get(position);
        holder.status.setText(order.getStatus());
        holder.status.setTextColor(ContextCompat.getColor(context,R.color.orange));
        holder.total.setText(String.valueOf(order.getTotal()));
        holder.productname.setText(order.getProductname());
        holder.quantity.setText(String.valueOf(order.getQuantity()));
        holder.price.setText(String.valueOf(order.getPrice()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productname,price,quantity,status,total;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.txt_productname);
            price = itemView.findViewById(R.id.txt_price);
            quantity = itemView.findViewById(R.id.txt_quantity);
            status = itemView.findViewById(R.id.txt_status);
            total = itemView.findViewById(R.id.txt_totalPlace);
        }
    }
}
