package com.example.gyeongsuk.underthefield.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gyeongsuk.underthefield.R;

public class SplashActivity extends AppCompatActivity {

    TextView loadingText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadingText = (TextView) findViewById(R.id.loadingTv);

        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(),3000);

    }

    private class splashHandler implements Runnable{

        @Override
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }
    }
}
