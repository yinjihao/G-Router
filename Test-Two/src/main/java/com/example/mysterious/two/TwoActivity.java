package com.example.mysterious.two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mysterious.grouter_annotation.annotation.annotation.RouterPage;

@RouterPage(path = "com.example.router.two")
public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }
}
