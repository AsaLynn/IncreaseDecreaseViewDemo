package com.zxn.crease;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zxn on 2019/4/17.
 */
public class CreaseButton extends View {


    private Paint mPaint;
    private CharSequence mText = "";
    //private ColorStateList mTextColor;
    private int mTextColor;
    private float mTextSize;
    private int mTextHeight;
    private Drawable mTextDrawable;

    public CreaseButton(Context context) {
        this(context, null);
    }

    public CreaseButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CreaseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //android.R.styleable#CreaseButton_text

        getAttributeSet(attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);

        if (null != mTextDrawable) {

            mTextDrawable.draw(canvas);
        }
    }

    private void getAttributeSet(AttributeSet attrs) {
        TypedArray typedArray
                = getContext()
                .obtainStyledAttributes(attrs, R.styleable.CreaseButton);
        if (typedArray != null) {
            mText = typedArray.getText(R.styleable.CreaseButton_text);
            //mTextColor = typedArray.getColorStateList(R.styleable.CreaseButton_textColor);
            mTextColor = typedArray.getColor(R.styleable.CreaseButton_textColor, Color.BLACK);
            mTextSize = typedArray.getDimension(R.styleable.CreaseButton_textSize, 80);
            mTextDrawable = typedArray.getDrawable(R.styleable.CreaseButton_textDrawable);

            //todo:获取mTextDrawable的尺寸.
            //mTextDrawable.setBounds(0,0,mTextDrawable.getIntrinsicWidth(),mTextDrawable.getIntrinsicHeight());
        }
    }

    private String TAG = "CreaseButton";

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(mTextColor);
        Log.e(TAG, "mTextSize: " + mTextSize);
        mPaint.setTextSize(mTextSize);

        Rect rect = new Rect();
        mPaint.getTextBounds(mText.toString(), 0, mText.length(), rect);
        mTextHeight = rect.height();
    }

    public void drawText(Canvas canvas) {
        float x = getWidth() / 2 - mPaint.measureText(mText.toString()) / 2;

        //int measuredHeight = getMeasuredHeight();
//        float y = getHeight() / 2 + mTextSize/2;
//        float y = getHeight() / 2 + mPaint.measureText(mText.toString())/2;
        float y = getHeight() / 2 + mTextHeight / 2;
        Log.e(TAG, "getHeight: " + getHeight());
        Log.e(TAG, "drawTexty: " + y);

        canvas.drawText(mText.toString(), x, y, mPaint);
    }
}
