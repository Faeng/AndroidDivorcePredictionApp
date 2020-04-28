package com.example.androidpredictionapp;

import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.provider.Settings.System.getString;


public class PredictionListPageAdapter extends RecyclerView.Adapter<PredictionListPageAdapter.MyViewHolder> {

    public List<Prediction> predictionList;
    private OnItemClickListener mListener;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

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
        TextView textViewMain,textViewSub,textViewSubPercent,textYesViewSub,textYesViewSubPercent;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewMain = itemView.findViewById(R.id.mainPredictResultTitle);
            textViewSub = itemView.findViewById(R.id.subPredictResultTitle);
            textViewSubPercent = itemView.findViewById(R.id.subPredictPercentTitle);
            textYesViewSub = itemView.findViewById(R.id.subPredictYesResultTitle);
            textYesViewSubPercent = itemView.findViewById(R.id.subPredictYesPercentTitle);
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
        float classNo = Float.parseFloat(predictionList.get(position).getClassNo());
        float classYes = Float.parseFloat(predictionList.get(position).getClassYes());
        holder.textViewSub.setText(R.string.predict_no_result);
        holder.textViewSubPercent.setText(df2.format(classNo*100)+" %");
        holder.textYesViewSub.setText( R.string.predict_yes_result);
        holder.textYesViewSubPercent.setText(df2.format(classYes*100)+" %");

    }


    @Override
    public int getItemCount() {
        return predictionList.size();
    }


}
