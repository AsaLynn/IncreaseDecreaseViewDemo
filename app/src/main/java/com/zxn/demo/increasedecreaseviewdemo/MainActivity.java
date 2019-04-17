package com.zxn.demo.increasedecreaseviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zxn.crease.CreaseView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected CreaseView creaseview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.creaseview) {

        }
    }

    private void initView() {
        creaseview = (CreaseView) findViewById(R.id.creaseview);
        creaseview.setMaxNum(10);
    }
}
