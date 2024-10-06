package poly.duan.fastfoodie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.Log;
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

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.viewHolder> {
    List<Product> list_pro;
    Context context;

    public ListFoodAdapter(List<Product> list_pro) {
        this.list_pro = list_pro;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listfood,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Product product = list_pro.get(position);

        TypedArray images = context.getResources().obtainTypedArray(R.array.product_images_pizza);

        // Gán ảnh từ danh sách tài nguyên drawable cho từng sản phẩm
        holder.img_picList.setImageResource(images.getResourceId(position, -1));
        images.recycle();

        holder.txt_titleFood.setText(list_pro.get(position).getProductname());
        holder.priceFood.setText(String.valueOf(list_pro.get(position).getPrice()));

        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("productId",product);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list_pro.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img_picList;
        TextView txt_titleFood,priceFood;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img_picList = itemView.findViewById(R.id.img_listFood);
            txt_titleFood = itemView.findViewById(R.id.title_txt);
            priceFood = itemView.findViewById(R.id.price_txt);
        }
    }
}
