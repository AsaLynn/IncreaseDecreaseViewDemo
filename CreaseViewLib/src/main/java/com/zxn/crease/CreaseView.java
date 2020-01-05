package com.zxn.crease;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zxn on 2019/4/17.
 */
public class CreaseView extends RelativeLayout implements View.OnClickListener, TextWatcher {

    protected TextView tvDecrease;
    protected EditText tvNum;
    protected TextView tvIncrease;
    protected LinearLayout llDecrease;
    protected LinearLayout llIncrease;
    private int mCurrentNum = 0;
    private int mMaxNum = Integer.MAX_VALUE;
    private int mMinNum = 0;
    private OnCreaseChangeListener mOnCreaseChangeListener;
    private boolean mDecreaseEnabled;
    private boolean mIncreaseEnabled;
    private Drawable mDecreaseDrawable;
    private Drawable mIncreaseDrawable;
    private Drawable mNumBackgroundDrawable;
    private ColorStateList mColorStateList;
    private boolean mNumEditable = true;

    public CreaseView(Context context) {
        this(context, null);
    }

    public CreaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CreaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInit(attrs);
    }

    private void onInit(AttributeSet attrs) {
        LayoutInflater.from(getContext())
                .inflate(R.layout.layout_crease_view, this);


        TypedArray typedArray
                = getContext()
                .obtainStyledAttributes(attrs, R.styleable.CreaseView);
        if (null != typedArray) {

            mNumBackgroundDrawable = typedArray.getDrawable(R.styleable.CreaseView_numBackground);

            mDecreaseDrawable = typedArray.getDrawable(R.styleable.CreaseView_decreaseIcon);

            mIncreaseDrawable = typedArray.getDrawable(R.styleable.CreaseView_increaseIcon);

            mColorStateList = typedArray.getColorStateList(R.styleable.CreaseView_buttonTextColor);

            mNumEditable = typedArray.getBoolean(R.styleable.CreaseView_numEditable, true);
            typedArray.recycle();
        }

        initView();


        //final Drawable d = a.getDrawable(R.styleable.ImageView_src);
        // background = a.getDrawable(attr);

//        tvNum.setBackgroundTintList();

        //tvNum.setBackground(null);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_decrease) {//-
            mCurrentNum--;
            if (mCurrentNum == mMinNum && mDecreaseEnabled) {
                mDecreaseEnabled = !mDecreaseEnabled;
                setDecreaseEnabled();
            }
            if (/*mCurrentNum > mMinNum && */mCurrentNum < mMaxNum && !mIncreaseEnabled) {
                mIncreaseEnabled = !mIncreaseEnabled;
                setIncreaseEnabled();
            }
            tvNum.setText(String.valueOf(mCurrentNum));
            tvNum.setSelection(tvNum.getText().length());
            tvNum.setCursorVisible(false);
            if (mOnCreaseChangeListener != null) {
                mOnCreaseChangeListener.onCreasedChanged(tvNum, mCurrentNum);
            }
        } else if (view.getId() == R.id.ll_increase) {//+
            mCurrentNum++;
            if (mCurrentNum == mMaxNum && mIncreaseEnabled) {
                mIncreaseEnabled = !mIncreaseEnabled;
                setIncreaseEnabled();
            }
            if (mCurrentNum > mMinNum && !mDecreaseEnabled) {
                mDecreaseEnabled = !mDecreaseEnabled;
                setDecreaseEnabled();
            }
            tvNum.setText(String.valueOf(mCurrentNum));
            tvNum.setSelection(tvNum.getText().length());
            tvNum.setCursorVisible(false);
            if (mOnCreaseChangeListener != null) {
                mOnCreaseChangeListener.onCreasedChanged(tvNum, mCurrentNum);
            }
        }
    }

    private void initView() {
        tvDecrease = findViewById(R.id.tv_decrease);
        if (null != mColorStateList){
            tvDecrease.setTextColor(mColorStateList);
        }
        if (null != mDecreaseDrawable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvDecrease.setBackground(mDecreaseDrawable);
            } else {
                tvDecrease.setBackgroundDrawable(mDecreaseDrawable);
            }
        }

        tvNum = findViewById(R.id.tv_num);
        tvNum.setText(String.valueOf(mCurrentNum));
        if (mNumEditable){
            tvNum.addTextChangedListener(this);
            tvNum.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        }else {
            tvNum.setInputType(EditorInfo.TYPE_NULL);
        }

        if (null != mNumBackgroundDrawable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvNum.setBackground(mNumBackgroundDrawable);
            } else {
                tvNum.setBackgroundDrawable(mNumBackgroundDrawable);
            }

        }

        tvIncrease = findViewById(R.id.tv_increase);
        if (null != mColorStateList){
            tvIncrease.setTextColor(mColorStateList);
        }
        if (null != mIncreaseDrawable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvIncrease.setBackground(mIncreaseDrawable);
            } else {
                tvIncrease.setBackgroundDrawable(mIncreaseDrawable);
            }

        }
        llDecrease = (LinearLayout) findViewById(R.id.ll_decrease);
        llDecrease.setOnClickListener(CreaseView.this);
        llIncrease = (LinearLayout) findViewById(R.id.ll_increase);
        llIncrease.setOnClickListener(CreaseView.this);

        mDecreaseEnabled = mCurrentNum > mMinNum;
        // mDecreaseEnabled = false;
        setDecreaseEnabled();
        mIncreaseEnabled = mCurrentNum < mMaxNum;
        setIncreaseEnabled();

    }

    public void setOnCreaseChangeListener(OnCreaseChangeListener listener) {
        this.mOnCreaseChangeListener = listener;
    }

    public int getNum() {
        return mCurrentNum;
    }

    public void setNum(int mCurrentNum) {
        this.mCurrentNum = mCurrentNum;
        tvNum.setText(String.valueOf(mCurrentNum));
        tvNum.setSelection(tvNum.getText().length());
        mDecreaseEnabled = mCurrentNum > mMinNum;
        mIncreaseEnabled = mCurrentNum < mMaxNum;

        setDecreaseEnabled();
        setIncreaseEnabled();
//        if (mOnCreaseChangeListener != null) {
//            mOnCreaseChangeListener.onCreasedChanged(tvNum, mCurrentNum);
//        }
    }

    public void setNumWithListener(int mCurrentNum) {
        this.mCurrentNum = mCurrentNum;
        tvNum.setText(String.valueOf(mCurrentNum));
        tvNum.setSelection(tvNum.getText().length());
        mDecreaseEnabled = mCurrentNum > mMinNum;
        mIncreaseEnabled = mCurrentNum < mMaxNum;

        setDecreaseEnabled();
        setIncreaseEnabled();

        if (mOnCreaseChangeListener != null) {
            mOnCreaseChangeListener.onCreasedChanged(tvNum, mCurrentNum);
        }
    }

    public int getMaxNum() {
        return mMaxNum;
    }

    public void setMaxNum(int mMaxNum) {
        this.mMaxNum = mMaxNum;
        mIncreaseEnabled = mCurrentNum < mMaxNum;
        setIncreaseEnabled();
    }

    public int getMinNum() {
        return mMinNum;
    }

    public void setMinNum(int mMinNum) {
        this.mMinNum = mMinNum;
        mDecreaseEnabled = mCurrentNum > mMinNum;
        setDecreaseEnabled();
    }

    private void setDecreaseEnabled() {
        tvDecrease.setEnabled(mDecreaseEnabled);
        llDecrease.setClickable(mDecreaseEnabled);

    }

    private void setIncreaseEnabled() {
        tvIncrease.setEnabled(mIncreaseEnabled);
        llIncrease.setClickable(mIncreaseEnabled);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s)) {
            int inputNum = Integer.parseInt(s.toString());
            if (inputNum > mMaxNum) {
                mCurrentNum = mMaxNum;
                Toast.makeText(getContext(), "输入最大值不可超过" + mMaxNum, Toast.LENGTH_LONG).show();
                tvNum.setText(String.valueOf(mCurrentNum));
                tvNum.setSelection(tvNum.getText().length());
            } else if (inputNum < mMinNum) {
                mCurrentNum = mMinNum;
                Toast.makeText(getContext(), "输入最小值不可低于" + mMinNum, Toast.LENGTH_SHORT).show();
                tvNum.setText(String.valueOf(mCurrentNum));
                tvNum.setSelection(tvNum.getText().length());
            } else {
                mCurrentNum = inputNum;
                if (mOnCreaseChangeListener != null) {
                    mOnCreaseChangeListener.onCreasedChanged(tvNum, mCurrentNum);
                }
            }

            if (mCurrentNum == mMaxNum && mIncreaseEnabled) {
                mIncreaseEnabled = !mIncreaseEnabled;
                setIncreaseEnabled();
            } /*else if (mCurrentNum >= mMinNum && mCurrentNum < mMaxNum) {
                mIncreaseEnabled = true;
                setIncreaseEnabled();
            }*/

            if (mCurrentNum < mMaxNum && !mIncreaseEnabled) {
                mIncreaseEnabled = !mIncreaseEnabled;
                setIncreaseEnabled();
            }
            if (mCurrentNum > mMinNum && !mDecreaseEnabled) {
                mDecreaseEnabled = !mDecreaseEnabled;
                setDecreaseEnabled();
            }

            if (mCurrentNum == mMinNum && mDecreaseEnabled) {
                mDecreaseEnabled = !mDecreaseEnabled;
                setDecreaseEnabled();
            }

            //tvNum.setText(String.valueOf(mCurrentNum));
//            if (mOnCreaseChangeListener != null) {
//                mOnCreaseChangeListener.onCreasedChanged(tvNum, mCurrentNum);
//            }
        }

    }

    public void setCursorVisible(boolean visible) {
        tvNum.setCursorVisible(visible);

    }

    public boolean isInputEmpty() {
        return TextUtils.isEmpty(tvNum.getText());
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
}
