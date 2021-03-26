package com.example.anagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv_info,tv_word;
    int index = 0, score = 0;
    EditText et_guess;
    Button b_next;
    Random r;
    String currentWord;
    ArrayList<String> dictionary;
    int level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        level = intent.getIntExtra("level",1);
        initializeDictionary();

        setContentView(R.layout.activity_main);
        tv_info = findViewById(R.id.tv_info);
        tv_word = findViewById(R.id.tv_word);
        et_guess = findViewById(R.id.et_guess);
        b_next = findViewById(R.id.b_next);
        showWord();
    }

    public void nextWord(View v){
        if (et_guess.getText().toString().equalsIgnoreCase(currentWord)) {
            score++;
        }
        if(b_next.getText().toString().equals("Finish")){
            Intent intent = new Intent(this,ScoreActivity.class);
            intent.putExtra("score",score);
            startActivity(intent);
            finish();
        }else {
            showWord();
        }
    }



    //shuffle algo
    private String shuffleWord(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled="";
        for(String letter:letters) {
            shuffled += letter;
        }
        return shuffled;
    }

    private void showWord(){
        if(index<=9) {
            currentWord = dictionary.get(index++);
        }
        tv_word.setText(shuffleWord(currentWord));
        et_guess.setText("");
        if(index==10){
            b_next.setText("Finish");
        }
    }

    private void initializeDictionary(){
        dictionary = new ArrayList<>();
        try {
            InputStream is = getResources().openRawResource(R.raw.dict);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String str = reader.readLine();
            while(str != null) {
                if (level == 1 && (str.length() == 5 || str.length() == 6)) {
                    dictionary.add(str);
                } else if (level == 2 && (str.length() == 7 || str.length() == 8)) {
                    dictionary.add(str);
                } else if (level == 3 && str.length() > 8) {
                    dictionary.add(str);
                }
                str = reader.readLine();
            }
        }catch(Exception ex){
            Log.e("MainActivity",ex.toString());
        }
        Collections.shuffle(dictionary);
        while(dictionary.size()>10){
            dictionary.remove(0);
        }
        for(String word : dictionary) {
            Log.e("MainActivity", word);
        }
    }

}
