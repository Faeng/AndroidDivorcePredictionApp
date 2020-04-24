package com.example.androidpredictionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter. FlowerViewHolder>{
    private int image[], name[], meaning[];

    public FlowerAdapter(int[] image, int[] name, int[] meaning) {
        this.image = image;
        this.name = name;
        this.meaning = meaning;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.list_flower_layout, parent, false);

        return new FlowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {
        holder.imageView.setImageResource(image[position]);
        holder.fName.setText(name[position]);
        holder.fMeaning.setText(meaning[position]);
    }

    @Override
    public int getItemCount() {
        return image.length;
    }

    public class FlowerViewHolder extends RecyclerView.ViewHolder {
        TextView fName , fMeaning;
        ImageView imageView;
        public FlowerViewHolder(@NonNull View itemView) {
            super(itemView);
            fName = itemView.findViewById(R.id.fName);
            fMeaning = itemView.findViewById(R.id.fMean);
            imageView = itemView.findViewById(R.id.fImage);
        }
    }
}
