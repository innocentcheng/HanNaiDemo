package com.example.scada.hannaidemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.scada.hannaidemo.R;
import com.example.scada.hannaidemo.untils.Tools;

/**
 * Created by Scada on 2017/10/24.
 */

public class RinglikeView extends View {
    private float mPeakAngle; //波峰角度
    private float mTroughAngle; //波谷角度
    private float mSpikeAngle; //波尖角度
    private float mFlatAngle; //平电量角度
    private int mStrokeWidth; //笔触大小
    private int mTotal; //总量
    private Paint mPaint = new Paint();
    private RectF mRectF = new RectF();
    private float mRadius = Tools.dipToPixel(55);

    public RinglikeView(Context context) {
        super(context);
    }

    public RinglikeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RinglikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(Tools.dipToPixel(20));
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Tools.dipToPixel(25));
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorOrange));
        mRectF.set(centerX - mRadius, centerY - mRadius, centerX + mRadius, centerY + mRadius);
        canvas.drawArc(mRectF, 120, mPeakAngle, false, mPaint);

        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Tools.dipToPixel(25));
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorTextBlue));
        mRectF.set(centerX - mRadius, centerY - mRadius, centerX + mRadius, centerY + mRadius);
        canvas.drawArc(mRectF, 120 + mPeakAngle, mTroughAngle, false, mPaint);

        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Tools.dipToPixel(25));
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mRectF.set(centerX - mRadius, centerY - mRadius, centerX + mRadius, centerY + mRadius);
        canvas.drawArc(mRectF, 120 + mPeakAngle + mTroughAngle, mSpikeAngle, false, mPaint);

        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Tools.dipToPixel(25));
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorGreen));
        mRectF.set(centerX - mRadius, centerY - mRadius, centerX + mRadius, centerY + mRadius);
        canvas.drawArc(mRectF, 120 + mPeakAngle + mTroughAngle + mSpikeAngle, mFlatAngle, false, mPaint);

        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorTextBlue));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(Tools.dipToPixel(2));
        canvas.drawText("" + mTotal, centerX, centerY + mPaint.descent(), mPaint);
    }

    public float getPeakAngle() {
        return mPeakAngle;
    }

    public RinglikeView setPeakAngle(float PeakAngle) {
        mPeakAngle = PeakAngle;
        return this;
    }

    public float getTroughAngle() {
        return mTroughAngle;
    }

    public RinglikeView setTroughAngle(float TroughAngle) {
        mTroughAngle = TroughAngle;
        return this;
    }

    public float getSpikeAngle() {
        return mSpikeAngle;
    }

    public RinglikeView setSpikeAngle(float SpikeAngle) {
        mSpikeAngle = SpikeAngle;
        return this;
    }

    public float getFlatAngle() {
        return mFlatAngle;
    }

    public RinglikeView setFlatAngle(float FlatAngle) {
        mFlatAngle = FlatAngle;
        return this;
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public RinglikeView setStrokeWidth(int StrokeWidth) {
        mStrokeWidth = StrokeWidth;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public RinglikeView setTotal(int Total) {
        mTotal = Total;
        return this;
    }
}
