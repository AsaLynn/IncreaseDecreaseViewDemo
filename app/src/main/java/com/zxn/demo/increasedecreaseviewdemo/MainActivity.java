package com.zxn.demo.increasedecreaseviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zxn.crease.CreaseView;
import com.zxn.utils.SoftKeyBoardManager;

//CreaseActivity
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
                creaseview.setMinNum(0);
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
        creaseview.setMinNum(0);
        creaseview.setNum(1);
        creaseview.setOnCreaseChangeListener(new CreaseView.OnCreaseChangeListener() {
            @Override
            public void onCreasedChanged(View view, int num) {
                //Toast.makeText(MainActivity.this, "num:" + num, Toast.LENGTH_SHORT).show();
                Log.i("MainActivity", "onCreasedChanged: -->" + num);
            }
        });

        CreaseView cv3 = (CreaseView) findViewById(R.id.cv3);
        cv3.setNum(5);
        cv3.setCreaseClickEnabled(false);

        CreaseView cv4 = (CreaseView) findViewById(R.id.cv4);
        cv4.setCreaseClickEnabled(true);
        cv4.setMaxNum(6);
        cv4.setMinNum(1);
        cv4.setNum(1);

        CreaseView cv5 = (CreaseView) findViewById(R.id.cv5);
        cv5.setMaxNum(5);
        cv5.setMinNum(1);
        cv5.setNum(5);
        cv5.setOnNumClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show();
                CreaseActivity.jumpTo(MainActivity.this);
            }
        });
    }
}
