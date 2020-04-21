package com.example.androidpredictionapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PredictionPageAdapter extends RecyclerView.Adapter<PredictionPageAdapter.MyViewHolder>{

    public ArrayList<View> itemViewList = new ArrayList<>();
    public int choices[];
    private OnItemClickListener mListener;

    public PredictionPageAdapter(int[] choices) {
        this.choices = choices;
    }

    public void setColor(int position){
        for (int i = 0; i < choices.length; i++) {
            if(i == position){

                itemViewList.get(i).setBackgroundColor(Color.parseColor("#567845"));
            }else{
                itemViewList.get(i).setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_choice_layout, parent, false);
        PredictionPageAdapter.MyViewHolder holder = new PredictionPageAdapter.MyViewHolder(v, mListener);
        itemViewList.add(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.choiceText.setText(choices[position]);
    }

    @Override
    public int getItemCount() {
        return choices.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView choiceText;
        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            choiceText = itemView.findViewById(R.id.choiceText);
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
}
