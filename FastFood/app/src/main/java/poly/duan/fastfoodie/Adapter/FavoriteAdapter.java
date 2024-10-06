package poly.duan.fastfoodie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import poly.duan.fastfoodie.Activity.DetailActivity;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.R;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.viewHolder> {
    List<Product> productList;
    Context context;

    public FavoriteAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TypedArray images = context.getResources().obtainTypedArray(R.array.product_images);

        // Gán ảnh từ danh sách tài nguyên drawable cho từng sản phẩm
        holder.pic_list.setImageResource(images.getResourceId(position, -1));

        images.recycle();

        Product product = productList.get(position);

        holder.txt_nameList.setText(productList.get(position).getProductname());
        holder.txt_priceList.setText(String.valueOf(productList.get(position).getPrice()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("productId",product);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView pic_list;
        TextView txt_nameList,txt_priceList;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            pic_list = itemView.findViewById(R.id.pic_list);
            txt_nameList = itemView.findViewById(R.id.txt_nameList);
            txt_priceList = itemView.findViewById(R.id.txt_priceList);
        }
    }
}
