package com.example.androidpredictionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.tensorflow.lite.Interpreter;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PredictionPage extends AppCompatActivity {

    private TextView question,questionBox;

    //use this for checking count of picking choices in items.
    private ArrayList<Integer> answer = new ArrayList<>();

    // list for keeping the choices for prediction
    float[][] input = new float[][]{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    //this array is for keeping the probability and class that is was predicted
    float[][] out = new float[][]{{0,0}};
    Interpreter tflite;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private RecyclerView choiceRecycle;
    private String no = "Question ";
    private RecyclerView.LayoutManager mListView;
    private PredictionPageAdapter adapter;
    private int number = 0;
    private int ans = -1;
    private int[] questions ={R.string.item_1,R.string.item_2,R.string.item_3,R.string.item_4,R.string.item_5,
            R.string.item_6,R.string.item_7,R.string.item_8,R.string.item_9,R.string.item_10,R.string.item_11,
            R.string.item_12,R.string.item_13,R.string.item_14,R.string.item_15,R.string.item_16,R.string.item_17,
            R.string.item_18,R.string.item_19,R.string.item_20,R.string.item_21,R.string.item_22,R.string.item_23,
            R.string.item_24,R.string.item_25,R.string.item_26,R.string.item_27,R.string.item_28,R.string.item_29,
            R.string.item_30,R.string.item_31,R.string.item_32,R.string.item_33,R.string.item_34,R.string.item_35,
            R.string.item_36,R.string.item_37,R.string.item_38,R.string.item_39,R.string.item_40,R.string.item_41,
            R.string.item_42,R.string.item_43,R.string.item_44,R.string.item_45,R.string.item_46};
    private int[] choice = {R.string.choiceLevel_0,R.string.choiceLevel_1,R.string.choiceLevel_2,
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
                        addChoiceInArray(number,ans);
                        number++;
                        buildRecycleView();
                    } else {
                        Toast.makeText(PredictionPage.this, "Please Select Answer", Toast.LENGTH_LONG).show();
                    }

                }else if (answer.size() == 44){
                    if (ans != -1) {
                        answer.add(ans);
                        ans = -1;
                        addChoiceInArray(number,ans);
                        number++;
                        buildRecycleView();
                    } else {
                        Toast.makeText(PredictionPage.this, "Please Select Answer", Toast.LENGTH_LONG).show();
                    }
                    btn.setText("Predict");
                }else if (answer.size() == 45){
                    if (ans != -1) {
                        answer.add(ans);
                        addChoiceInArray(45,ans);
                        // predict here
                        try{
                            tflite = new Interpreter(loadModelFile());
                        }catch(Exception e){
                            System.out.println("ERROR TO LOAD MODEL FILE");
                            e.printStackTrace();
                        }
                        tflite.run(input,out);
                        System.out.println("The prob that you will not divorce is: " + df2.format(out[0][0]*100)+"%");
                        System.out.println("The prob that you will divorce is: "+df2.format(out[0][1]*100)+"%");
//                        Toast.makeText(PredictionPage.this, "The prob that you will not divorce is: " +
//                                df2.format(out[0][0]*100)+"%"+"\n"+
//                                "The prob that you will divorce is: "+df2.format(out[0][1]*100)+"%", Toast.LENGTH_LONG).show();
                        //showPredictResultPage(out[0][0],out[0][1]);
                        Intent i = new Intent(PredictionPage.this, ResultPredictionPage.class);
                        i.putExtra("predictResult",new float[]{out[0][0],out[0][1]});
                        startActivity(i);
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
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd("AndroidDivorcedPrediction.tflite");
        FileInputStream fileInputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffset,declaredLength);
    }

    public void addChoiceInArray(int number,int choice){
        input[0][number] = choice;
    }

//    public void showPredictResultPage(float class_a, float class_b){
//        if(class_a > class_b){
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//            View result_layout = LayoutInflater.from(this).inflate(R.layout.result_prediction_layout,null);
//            final TextView result_text = result_layout.findViewById(R.id.resultTextView);
//            final TextView percent_value = result_layout.findViewById(R.id.percentValueTextview);
//            result_text.setText(R.string.predict_no_result);
//            percent_value.setText(String.valueOf(df2.format(class_a*100)));
//            builder.setView(result_layout);
//            builder.show();
//        }
//        else{
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            View result_layout = LayoutInflater.from(this).inflate(R.layout.result_prediction_layout,null);
//            final TextView result_text = result_layout.findViewById(R.id.resultTextView);
//            final TextView percent_value = result_layout.findViewById(R.id.percentValueTextview);
//            result_text.setText(R.string.predict_yes_result);
//            percent_value.setText(String.valueOf(df2.format(class_b*100)));
//            builder.setView(result_layout);
//            builder.show();
//        }
//    }
}
