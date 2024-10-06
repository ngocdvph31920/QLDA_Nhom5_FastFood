package poly.duan.fastfoodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import poly.duan.fastfoodie.Model.CartItem;
import poly.duan.fastfoodie.R;

public class PlaceOrderAdapter extends RecyclerView.Adapter<PlaceOrderAdapter.viewHolder> {
    List<CartItem> list_cartItem;
    Context context;

    public PlaceOrderAdapter(List<CartItem> list_cartItem) {
        this.list_cartItem = list_cartItem;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_placeorder,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txt_namePlace.setText(list_cartItem.get(position).getProductname());
        holder.txt_quantityPlace.setText(String.valueOf(list_cartItem.get(position).getQuantity()));
        holder.txt_pricePlace.setText(String.valueOf(list_cartItem.get(position).getPrice())); // Chỉnh sửa từ txt_quantityPlace thành txt_pricePlace
        holder.txt_totalPlace.setText(String.valueOf(list_cartItem.get(position).getTotal_order()));

    }

    @Override
    public int getItemCount() {
        return list_cartItem.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_namePlace,txt_quantityPlace,txt_pricePlace,txt_totalPlace;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_namePlace = itemView.findViewById(R.id.txt_namePlace);
            txt_quantityPlace = itemView.findViewById(R.id.txt_quantityPlace);
            txt_pricePlace = itemView.findViewById(R.id.txt_pricePlace);
            txt_totalPlace = itemView.findViewById(R.id.total_Place);
        }
    }
}
