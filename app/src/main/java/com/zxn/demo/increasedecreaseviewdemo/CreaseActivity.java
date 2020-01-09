package com.zxn.demo.increasedecreaseviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zxn.crease.CreaseView;

public class CreaseActivity extends AppCompatActivity {

    public static void jumpTo(Context context) {
        Intent intent = new Intent(context, CreaseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crease);
        //CreaseView cv_layout = (CreaseView) findViewById(R.id.cv_layout);
    }
}
