package com.zxn.demo.increasedecreaseviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zxn.crease.CreaseView;
import com.zxn.utils.SoftKeyBoardManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected CreaseView creaseview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();

        SoftKeyBoardManager.setKeyBoardListener(this, new SoftKeyBoardManager.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                creaseview.setCursorVisible(true);
            }

            @Override
            public void keyBoardHide(int height) {
                creaseview.setMinNum(1);
                creaseview.setNum(1);
                creaseview.setCursorVisible(false);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.creaseview) {
            //#00BB9C
        }
    }

    private void initView() {
        creaseview = (CreaseView) findViewById(R.id.creaseview);
        creaseview.setMaxNum(10);
        creaseview.setMinNum(1);
        creaseview.setNum(1);
        creaseview.setOnCreaseChangeListener(new CreaseView.OnCreaseChangeListener() {
            @Override
            public void onCreasedChanged(View view, int num) {
                //Toast.makeText(MainActivity.this, "num:" + num, Toast.LENGTH_SHORT).show();
                Log.i("MainActivity", "onCreasedChanged: -->"+num);
            }
        });
    }
}
