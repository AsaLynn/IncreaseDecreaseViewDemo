package com.zxn.crease;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zxn on 2019/4/17.
 */
public class CreaseView extends RelativeLayout implements View.OnClickListener {

    protected TextView ivDecrease;
    protected TextView tvNum;
    protected TextView tvIncrease;
    protected LinearLayout llDecrease;
    protected LinearLayout llIncrease;
    private int mCurrentNum;
    private int mMaxNum = Integer.MAX_VALUE;
    private OnCreaseChangeListener mOnCreaseChangeListener;

    public CreaseView(Context context) {
        this(context, null);
    }

    public CreaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CreaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInit();
    }

    private void onInit() {
        LayoutInflater.from(getContext())
                .inflate(R.layout.layout_crease_view, this);
        initView(this);

        //final Drawable d = a.getDrawable(R.styleable.ImageView_src);
        // background = a.getDrawable(attr);

//        tvNum.setBackgroundTintList();

        //tvNum.setBackground(null);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_decrease) {//-
            mCurrentNum--;
            if (mCurrentNum == 0) {
                ivDecrease.setEnabled(false);
                llDecrease.setClickable(ivDecrease.isEnabled());
            }
            if (mCurrentNum > 0 && mCurrentNum < mMaxNum && !tvIncrease.isEnabled()) {
                tvIncrease.setEnabled(!tvIncrease.isEnabled());
                llIncrease.setClickable(tvIncrease.isEnabled());
            }
//            if (mCurrentNum < 0) {
//                mCurrentNum = 0;
//                return;
//            }
            tvNum.setText(String.valueOf(mCurrentNum));
            if (mOnCreaseChangeListener != null) {
                mOnCreaseChangeListener.onCreasedChanged(tvNum, mCurrentNum);
            }
        } else if (view.getId() == R.id.tv_num) {

        } else if (view.getId() == R.id.ll_increase) {//+
            mCurrentNum++;
            if (mCurrentNum == mMaxNum) {
                tvIncrease.setEnabled(false);
                llIncrease.setClickable(tvIncrease.isEnabled());
            }
            if (mCurrentNum > 0 && !ivDecrease.isEnabled()) {
                ivDecrease.setEnabled(!ivDecrease.isEnabled());
                llDecrease.setClickable(ivDecrease.isEnabled());
            }
//            if (mCurrentNum > mMaxNum) {
//                mCurrentNum = mMaxNum;
//                return;
//            }
            tvNum.setText(String.valueOf(mCurrentNum));
            if (mOnCreaseChangeListener != null) {
                mOnCreaseChangeListener.onCreasedChanged(tvNum, mCurrentNum);
            }
        }
    }

    private void initView(View rootView) {
        ivDecrease = findViewById(R.id.tv_decrease);
        tvNum = (TextView) findViewById(R.id.tv_num);
        tvNum.setText(String.valueOf(mCurrentNum));

//        ivDecrease.setEnabled(mCurrentNum > 0);
//        tvIncrease.setEnabled(mCurrentNum < mMaxNum);

        tvIncrease = findViewById(R.id.tv_increase);
        llDecrease = (LinearLayout) findViewById(R.id.ll_decrease);
        llDecrease.setOnClickListener(CreaseView.this);
        llIncrease = (LinearLayout) findViewById(R.id.ll_increase);
        llIncrease.setOnClickListener(CreaseView.this);
    }

    public interface OnCreaseChangeListener {
        /**
         * Called when the mCurrentNum has changed.
         *
         * @param view The mCurrentNum view  whose state has changed.
         * @param num  The new num.
         */
        void onCreasedChanged(View view, int num);
    }

    public int getNum() {
        return mCurrentNum;
    }

    public void setNum(int mCurrentNum) {
        this.mCurrentNum = mCurrentNum;
        tvNum.setText(String.valueOf(mCurrentNum));
        ivDecrease.setEnabled(mCurrentNum > 0);
        tvIncrease.setEnabled(mCurrentNum < mMaxNum);
        if (mOnCreaseChangeListener != null) {
            mOnCreaseChangeListener.onCreasedChanged(tvNum, mCurrentNum);
        }
    }

    public int getMaxNum() {
        return mMaxNum;
    }

    public void setMaxNum(int mMaxNum) {
        this.mMaxNum = mMaxNum;
        ivDecrease.setEnabled(mCurrentNum > 0);
        tvIncrease.setEnabled(mCurrentNum < mMaxNum);
    }
}
