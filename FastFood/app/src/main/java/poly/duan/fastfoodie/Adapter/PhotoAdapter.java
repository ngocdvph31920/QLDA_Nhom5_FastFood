package poly.duan.fastfoodie.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.duan.fastfoodie.Model.Photo;
import poly.duan.fastfoodie.R;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.viewHolder> {
    Context context;
    ArrayList<Photo> list;

    public PhotoAdapter(Context context, ArrayList<Photo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       LayoutInflater inflater = ((Activity)context).getLayoutInflater();
       View view = inflater.inflate(R.layout.item_slide,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
       Photo photo = list.get(position);
       if(photo == null){
           return;
       }
       holder.img.setImageResource(photo.getImgae());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgslide);
        }
    }
}
