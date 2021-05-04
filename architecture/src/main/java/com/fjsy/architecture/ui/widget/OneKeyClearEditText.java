package com.fjsy.architecture.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.drawable.DrawableCompat;

import com.fjsy.architecture.R;

/**
 * from：wrz on 2019/8/31 13:51
 */
public class OneKeyClearEditText
        extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    private int OriginalPaddingLeft = -1;
    private String preFixText;
    private Drawable mClearDrawable;// 一键删除的按钮
    private int drawableColor = 0;
    private boolean hasFocus;// 控件是否有焦点

    public OneKeyClearEditText(Context context) {
        this(context, null);
    }

    public OneKeyClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    @SuppressLint("InlinedApi")
    public OneKeyClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.colorAccent});
        array.recycle();
        TypedArray array2 = context.obtainStyledAttributes(attrs, R.styleable.OneKeyClearEditText);
        drawableColor = array2.getColor(R.styleable.OneKeyClearEditText_deletecolor, -1);
        preFixText = array2.getString(R.styleable.OneKeyClearEditText_prefixText);
        preFixText = preFixText == null ? "" : preFixText;
        array2.recycle();
        initClearDrawable(context);
        setClearIconVisible(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        restPaddingLeft();
    }

    public void setPrefixText(String mPrefixString) {
        this.preFixText = mPrefixString;
        invalidate();
    }

    /**
     * 重新设置paddingLeft
     */
    private void restPaddingLeft() {
        if (OriginalPaddingLeft < 0) {
            final int length = preFixText.length();
            if (length <= 0) return;
            final double paddingLeft = getPaint().measureText(preFixText);
            OriginalPaddingLeft = getCompoundPaddingLeft();
            setPadding((int) (OriginalPaddingLeft + paddingLeft), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawPrefix(canvas);
    }

    private void onDrawPrefix(Canvas canvas) {
        final int length = preFixText.length();
        if (length <= 0) return;
        if (getText().toString().length() <= 0) return;

        TextPaint paint = new TextPaint();
        paint.setTextSize(getPaint().getTextSize());
        paint.setColor(Color.parseColor("#DB1A30"));

        Paint paint_util = new Paint();
        paint_util.setColor(Color.TRANSPARENT);

        int textY = getLineBounds(0, null);
        int textX = OriginalPaddingLeft;
        float prefixWidth = paint.measureText(preFixText);
        float textWidth = getPaint().measureText(getText().toString());
        if ((getGravity() & Gravity.LEFT) == Gravity.LEFT) {
            /*getScrollX()单行移动时x滑动的距离 否则绘制前缀会看不到*/
            textX = OriginalPaddingLeft + getScrollX();
        } else if ((getGravity() & Gravity.RIGHT) == Gravity.RIGHT) {
            textX = getScrollX() + OriginalPaddingLeft;
            float rv = getMeasuredWidth() - (textWidth + getCompoundPaddingRight() + getCompoundPaddingLeft());
            if (rv > 0) {
                textX = (int) (textX + rv);
            }
           /* Paint paint_util2 = new Paint();
            paint_util2.setColor(Color.argb(255, 0, 255, 255));
            canvas.drawRect(0, 0, getRight() / 2 + getScrollX(), getMeasuredHeight(), paint_util2);*/

        }

        float fontHeight = paint.getFontMetrics().descent - paint.getFontMetrics().ascent;
        float fontTop = textY + paint.getFontMetrics().ascent;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(textX, fontTop, textX + prefixWidth, fontHeight + fontTop, 10, 10, paint_util);
        } else {
            canvas.drawRect(textX, fontTop, textX + prefixWidth, fontHeight + fontTop, paint_util);
        }

        canvas.drawText(preFixText, textX, textY, paint);
    }

    private void log(String s) {
        Log.d(getClass().getSimpleName(), "tag" + getTag() + s);
    }

    @SuppressLint("NewApi")
    private void initClearDrawable(Context context) {
        mClearDrawable = getCompoundDrawables()[2];// 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.mipmap.ic_clear);
        }
        if (drawableColor != -1) {
            DrawableCompat.setTint(mClearDrawable, drawableColor);// 设置删除按钮的颜色和TextColor的颜色一致
        }
        mClearDrawable.setBounds(0, 0, (int) getTextSize(), (int) getTextSize());// 设置Drawable的宽高和TextSize的大小一致
        setClearIconVisible(true);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);

    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    private void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mClearDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            // 判断触摸点是否在水平范围内
            boolean isInnerWidth = (x > (getWidth() - getTotalPaddingRight()))
                    && (x < (getWidth() - getPaddingRight()));
            // 获取删除图标的边界，返回一个Rect对象
            Rect rect = mClearDrawable.getBounds();
            // 获取删除图标的高度
            int height = rect.height();
            int y = (int) event.getY();
            // 计算图标底部到控件底部的距离
            int distance = (getHeight() - height) / 2;
            // 判断触摸点是否在竖直范围内(可能会有点误差)
            // 触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
            boolean isInnerHeight = (y > distance) && (y < (distance + height));
            if (isInnerHeight && isInnerWidth) {
                this.setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (hasFocus) {
            setClearIconVisible(text.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            setClearIconVisible(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().toString().length() > 0);
        } else {
            setClearIconVisible(false);
        }

    }

}
