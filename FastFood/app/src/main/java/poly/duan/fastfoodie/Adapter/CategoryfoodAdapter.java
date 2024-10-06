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

import java.util.ArrayList;

import poly.duan.fastfoodie.Activity.ListFoodActivity;
import poly.duan.fastfoodie.Model.Category;
import poly.duan.fastfoodie.R;

public class CategoryfoodAdapter extends RecyclerView.Adapter<CategoryfoodAdapter.viewHolder> {
    ArrayList<Category> list_category;
    Context context;

    public CategoryfoodAdapter(ArrayList<Category> list_category) {
        this.list_category = list_category;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txt_cat.setText(list_category.get(position).getCategory());
        TypedArray images = context.getResources().obtainTypedArray(R.array.product_images_cat);

        // Gán ảnh từ danh sách tài nguyên drawable cho từng sản phẩm
        holder.img_cat.setImageResource(images.getResourceId(position, -1));

        images.recycle();
        switch (position) {
            case 0: {
                holder.img_cat.setBackgroundResource(R.drawable.cat_0_background);
                break;
            }
            case 1: {
                holder.img_cat.setBackgroundResource(R.drawable.cat_1_background);
                break;
            }
            case 2: {
                holder.img_cat.setBackgroundResource(R.drawable.cat_2_background);
                break;
            }
            case 3: {
                holder.img_cat.setBackgroundResource(R.drawable.cat_3_background);
                break;
            }
            case 4: {
                holder.img_cat.setBackgroundResource(R.drawable.cat_4_background);
                break;
            }
            case 5: {
                holder.img_cat.setBackgroundResource(R.drawable.cat_5_background);
                break;
            }
            case 6: {
                holder.img_cat.setBackgroundResource(R.drawable.cat_6_background);
                break;
            }
            case 7: {
                holder.img_cat.setBackgroundResource(R.drawable.cat_7_background);
                break;
            }

        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListFoodActivity.class);
            intent.putExtra("CategoryID",list_category.get(position).getId());
            intent.putExtra("CategoryName",list_category.get(position).getCategory());
//            intent.putExtra("Category",list_category.get(position));

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list_category.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_cat;
        ImageView img_cat;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_cat = itemView.findViewById(R.id.catName);
            img_cat = itemView.findViewById(R.id.img_category);
        }
    }
}
