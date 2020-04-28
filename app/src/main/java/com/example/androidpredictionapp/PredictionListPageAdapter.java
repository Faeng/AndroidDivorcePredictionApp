package com.example.androidpredictionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PredictionListPageAdapter extends RecyclerView.Adapter<PredictionListPageAdapter.MyViewHolder> {

    public List<Prediction> predictionList;
    private OnItemClickListener mListener;

    public PredictionListPageAdapter(List predictionList){
        this.predictionList = predictionList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMain,textViewSub;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewMain = itemView.findViewById(R.id.mainPredictResultTitle);
            textViewSub = itemView.findViewById(R.id.subPredictResultTitle);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_prediction_results_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PredictionListPageAdapter.MyViewHolder holder, int position) {
        holder.textViewMain.setText(predictionList.get(position).getDateTime());
        holder.textViewSub.setText(predictionList.get(position).getResult());
    }

    @Override
    public int getItemCount() {
        return predictionList.size();
    }


}
