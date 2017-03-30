package com.example.noahglaser.braintrainerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
    }

    public void play(View view)
    {
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
    }

}
