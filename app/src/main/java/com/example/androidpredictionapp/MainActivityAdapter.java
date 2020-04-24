package com.example.androidpredictionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder>{
    public int[] title;
    public int[] description;
    public int[] image;
    private OnItemClickListener mListener;


    public MainActivityAdapter(int[] image, int[] title, int[] description) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMain,textViewSub;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewMain = itemView.findViewById(R.id.mainTitle);
            textViewSub = itemView.findViewById(R.id.subTitle);
            imageView = itemView.findViewById(R.id.imageTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menu_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(v, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(image[position]);
        holder.textViewMain.setText(title[position]);
        holder.textViewSub.setText(description[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

}
