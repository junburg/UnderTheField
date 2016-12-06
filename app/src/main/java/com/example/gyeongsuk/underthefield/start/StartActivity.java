package com.example.gyeongsuk.underthefield.start;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gyeongsuk.underthefield.R;
import com.example.gyeongsuk.underthefield.main.MainActivity;

public class StartActivity extends AppCompatActivity {

    ImageView startIv;
    Button startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startIv = (ImageView)findViewById(R.id.startImage);
        startBtn = (Button)findViewById(R.id.startButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
