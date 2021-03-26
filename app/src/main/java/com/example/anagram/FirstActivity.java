package com.example.anagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class FirstActivity extends AppCompatActivity {
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        rg = findViewById(R.id.course);
    }

    public void showDetails(View view){
        int level = 0;
        switch(rg.getCheckedRadioButtonId()){
            case R.id.radioButton1:
                level = 1;
                break;
            case R.id.radioButton2:
                level = 2;
                break;
            case R.id.radioButton3:
                level = 3;
                break;
        }
        if(level != 0){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("level",level);
            startActivity(intent);
            finish();
        }
    }
}
