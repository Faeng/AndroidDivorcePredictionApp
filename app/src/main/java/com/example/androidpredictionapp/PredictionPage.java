package com.example.androidpredictionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PredictionPage extends AppCompatActivity {

    private TextView question,questionBox,nextText;
    private ArrayList<Integer> answer = new ArrayList<>();
    private RecyclerView choiceRecycle;
    private String no = "Question ";
    private RecyclerView.LayoutManager mListView;
    private PredictionPageAdapter adapter;
    private int number = 0;
    private int ans = -1;
    private int questions[] ={R.string.item_1,R.string.item_2,R.string.item_3,R.string.item_4,R.string.item_5,
            R.string.item_6,R.string.item_7,R.string.item_8,R.string.item_9,R.string.item_10,R.string.item_11,
            R.string.item_12,R.string.item_13,R.string.item_14,R.string.item_15,R.string.item_16,R.string.item_17,
            R.string.item_18,R.string.item_19,R.string.item_20,R.string.item_21,R.string.item_22,R.string.item_23,
            R.string.item_24,R.string.item_25,R.string.item_26,R.string.item_27,R.string.item_28,R.string.item_29,
            R.string.item_30,R.string.item_31,R.string.item_32,R.string.item_33,R.string.item_34,R.string.item_35,
            R.string.item_36,R.string.item_37,R.string.item_38,R.string.item_39,R.string.item_40,R.string.item_41,
            R.string.item_42,R.string.item_43,R.string.item_44,R.string.item_45,R.string.item_46};
    private int choice[] = {R.string.choiceLevel_0,R.string.choiceLevel_1,R.string.choiceLevel_2,
            R.string.choiceLevel_3,R.string.choiceLevel_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction_page);
        question = findViewById(R.id.question);
        questionBox = findViewById(R.id.questionBox);
        buildRecycleView();
        final Button btn = findViewById(R.id.buttonNext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer.size() < 44){

                    if (ans != -1) {
                        answer.add(ans);
                        ans = -1;
                        number++;
                        buildRecycleView();
                    } else {
                        Toast.makeText(PredictionPage.this, "Please Select Answer", Toast.LENGTH_LONG).show();
                    }

                }else if (answer.size() == 44){
                    if (ans != -1) {
                        answer.add(ans);
                        ans = -1;
                        number++;
                        buildRecycleView();
                    } else {
                        Toast.makeText(PredictionPage.this, "Please Select Answer", Toast.LENGTH_LONG).show();
                    }
                    btn.setText("Predict");
                }else if (answer.size() == 45){
                    if (ans != -1) {
                        answer.add(ans);
                        Toast.makeText(PredictionPage.this, ""+answer.size(), Toast.LENGTH_LONG).show();
                        // predict here
                    } else {
                        Toast.makeText(PredictionPage.this, "Please Select Answer", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void buildRecycleView(){
        question.setText(no+(number+1));
        questionBox.setText(questions[number]);
        choiceRecycle = findViewById(R.id.choice);
        choiceRecycle.setHasFixedSize(true);
        mListView = new LinearLayoutManager(this);
        adapter = new PredictionPageAdapter(choice);

        choiceRecycle.setLayoutManager(mListView);
        choiceRecycle.setAdapter(adapter);

        adapter.setOnItemClickListener(new PredictionPageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ans = position;
                adapter.setColor(position);
            }
        });

    }
}
