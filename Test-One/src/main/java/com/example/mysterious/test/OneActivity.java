package com.example.mysterious.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mysterious.grouter_annotation.annotation.annotation.RouterPage;
import com.example.mysterious.router.Router;

@RouterPage(path = "com.example.router.one")
public class OneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.startUri(OneActivity.this, "com.example.router.two");
            }
        });

        findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.startUri(OneActivity.this, "com.example.router.three");
            }
        });
    }
}
