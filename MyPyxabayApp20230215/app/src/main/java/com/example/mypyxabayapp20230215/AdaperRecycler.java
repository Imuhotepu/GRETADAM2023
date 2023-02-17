package com.example.mypyxabayapp20230215;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdaperRecycler extends RecyclerView.Adapter<AdaperRecycler.myViewHolder> {

    private Context context;
    private ArrayList<ModelItem> itemArrayList;

    public AdaperRecycler(Context context, ArrayList<ModelItem> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ModelItem currenteItem = itemArrayList.get(position);

        String imagURL = currenteItem.getImageURL();
        String creator = currenteItem.getCreator();
        int likes = currenteItem.getLikes();

        holder.tvCreater.setText(creator); //== holder.tvCreater.setText(currenteItem.getCreator());
        holder.tvLikes.setText("💕 " + likes);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round);

        //Context context1 = holder.ivImageView.getContext(); // Si erreur
        Glide.with(holder.ivImageView)
                .load(imagURL)
                .apply(options)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImageView);
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImageView;
        private TextView tvCreater, tvLikes;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImageView = itemView.findViewById(R.id.imageView);
            tvCreater = itemView.findViewById(R.id.tvCreater);
            tvLikes = itemView.findViewById(R.id.tvLikes);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myOnItemClickListener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            // myOnItemClickListener
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener myOnItemClickListener;

    public void setMyOnClickListener(OnItemClickListener onItemClickListener) {
        this.myOnItemClickListener = onItemClickListener;
    }
}
