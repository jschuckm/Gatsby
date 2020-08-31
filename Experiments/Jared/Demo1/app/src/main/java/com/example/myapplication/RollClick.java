package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class RollClick implements View.OnClickListener {
    int bound;
    TextView result;
    public RollClick(int bound, TextView textView){
        this.bound = bound;
        this.result = textView;
    }
    @Override
    public void onClick(View v){
        int random = new Random().nextInt(bound);
        result.setText(""+random);
    }
}
