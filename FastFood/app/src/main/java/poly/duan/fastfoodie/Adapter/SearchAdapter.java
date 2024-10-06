package poly.duan.fastfoodie.Adapter;

import android.content.Context;
import android.content.Intent;
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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.viewHolder> {
    List<Product> list;
    Context context;

    public SearchAdapter(List<Product> list) {
        this.list = list;
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
        Product product = list.get(position);

        holder.txt_titleSearch.setText(list.get(position).getProductname());
        holder.priceSearch.setText(String.valueOf(list.get(position).getPrice()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("productId",product);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img_picSearch;
        TextView txt_titleSearch,priceSearch;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img_picSearch = itemView.findViewById(R.id.img_listFood);
            txt_titleSearch = itemView.findViewById(R.id.title_txt);
            priceSearch = itemView.findViewById(R.id.price_txt);
        }
    }
}
