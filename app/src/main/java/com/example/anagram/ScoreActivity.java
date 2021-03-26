package com.example.anagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    int score;
    TextView tvMessage, tvScore;
    RatingBar bar;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        tvMessage = findViewById(R.id.tvMessage);
        bt = findViewById(R.id.button2);
        tvScore = findViewById(R.id.tvScore);
        bar = findViewById(R.id.ratingBar);
        bar.setIsIndicator(true);
        showScore();
    }

    private void showScore(){
        String message;
        if(score>=8){
            message = "Awesome! You did a great job!!";
        }
        else if(score>=5){
            message = "Good! but Practice More!!";
        }
        else{
                message = "Poor! You disappoint me!!";
        }
        tvMessage.setText(message);
        tvScore.setText("Score : "+score);
        bar.setRating(score);
    }

    public void close(View v){
        Intent intent = new Intent(this,FirstActivity.class);
        startActivity(intent);
        finish();
    }
    public void closer(View v){
        finish();
    }
}
