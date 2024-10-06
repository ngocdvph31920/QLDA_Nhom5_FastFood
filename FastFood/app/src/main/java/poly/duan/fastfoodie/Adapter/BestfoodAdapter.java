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

public class BestfoodAdapter extends RecyclerView.Adapter<BestfoodAdapter.viewHolder> {
    List<Product> list_food;
    Context context;

    public BestfoodAdapter(List<Product> list_food) {
        this.list_food = list_food;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_best_food,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Product product = list_food.get(position);

        holder.txt_productName.setText(list_food.get(position).getProductname());
        holder.txt_price.setText(String.valueOf(list_food.get(position).getPrice()+""+"đ"));

//         Lấy danh sách tài nguyên drawable
        TypedArray images = context.getResources().obtainTypedArray(R.array.product_images);

        // Gán ảnh từ danh sách tài nguyên drawable cho từng sản phẩm
        holder.img_food.setImageResource(images.getResourceId(position, -1));

        images.recycle();


//        Picasso.get().load(product.getImageproduct()).into(holder.img_food);

        holder.itemView.setOnClickListener(v -> {
//            String productId = li.get(position).getId();

            Intent intent = new Intent(context, DetailActivity.class);
            Log.d("product111111","678"+product.toString());
            intent.putExtra("productId",product);


            context.startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return list_food.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_productName,txt_description,txt_price;
        ImageView img_food;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_productName = itemView.findViewById(R.id.txt_productName);
//            txt_description = itemView.findViewById(R.id.txt_description);
            txt_price = itemView.findViewById(R.id.txt_price);
            img_food = itemView.findViewById(R.id.img_food);

        }
    }
}
