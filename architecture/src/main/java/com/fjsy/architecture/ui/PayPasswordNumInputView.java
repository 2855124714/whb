package com.fjsy.architecture.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.utils.DensityUtils;

public class PayPasswordNumInputView extends LinearLayout {
    private final Context context;

    private boolean isPreDraw = false;

    public PayPasswordNumInputView(Context context) {
        this(context, null);
    }

    /**
     * 展示当前输入数字
     */
    private RelativeLayout[] mRlViews;
    /**
     * 展示输入圆点
     */
    private ImageView[] mDotViews;
    /**
     * 预留的字符
     */
    private String mPreText;
    /**
     * 文字颜色
     */
    private int mTextColor;


    public PayPasswordNumInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumberInputView);
        mPreText = ta.getString(R.styleable.PayPasswordNumInputView_ps_text);
        mTextColor = ta.getColor(R.styleable.PayPasswordNumInputView_ps_text_color, Color.parseColor("#2780F8"));
        ta.recycle();
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_numer_input_bg, null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        this.addView(view);
        bindView(view);
        updateInputNum(mPreText);
    }

    /**
     * BindView
     *
     * @param view
     */
    private void bindView(View view) {
        mRlViews = new RelativeLayout[6];
        mRlViews[0] = view.findViewById(R.id.rv_1);
        mRlViews[1] = view.findViewById(R.id.rv_2);
        mRlViews[2] = view.findViewById(R.id.rv_3);
        mRlViews[3] = view.findViewById(R.id.rv_4);
        mRlViews[4] = view.findViewById(R.id.rv_5);
        mRlViews[5] = view.findViewById(R.id.rv_6);
        LinearLayout linear = view.findViewById(R.id.linear_all);
        linear.getViewTreeObserver().addOnPreDrawListener(() -> {
            if (!isPreDraw) {
                isPreDraw = true;
                int measuredWidth = linear.getMeasuredWidth();
                int relWidth = (measuredWidth - DensityUtils.dp2px(context, 60)) / 4;
                for (RelativeLayout mRlView : mRlViews) {
                    ViewGroup.LayoutParams itemLayoutParams = mRlView.getLayoutParams();
                    itemLayoutParams.width = relWidth;
                    itemLayoutParams.height = relWidth;
                    mRlView.setLayoutParams(itemLayoutParams);
                }
            }
            return true;
        });


//        for (int i = 0; i < mRlViews.length; i++) {
//            mRlViews[i].setTextColor(mTextColor);
//        }

        mDotViews = new ImageView[6];
        mDotViews[0] = (ImageView) view.findViewById(R.id.iv_number_input_num_1);
        mDotViews[1] = (ImageView) view.findViewById(R.id.iv_number_input_num_2);
        mDotViews[2] = (ImageView) view.findViewById(R.id.iv_number_input_num_3);
        mDotViews[3] = (ImageView) view.findViewById(R.id.iv_number_input_num_4);
        mDotViews[4] = (ImageView) view.findViewById(R.id.iv_number_input_num_5);
        mDotViews[5] = (ImageView) view.findViewById(R.id.iv_number_input_num_6);
    }

    /**
     * 更新界面显示
     */
    public void updateInputNum(String luckNum) {

        if (TextUtils.isEmpty(luckNum)) {
//            for (TextView textView : mRlViews) {
//                textView.setText("");
//            }
            for (ImageView dotView : mDotViews) {
                dotView.setVisibility(GONE);
            }
            return;
        }
//        for (TextView textView : mRlViews) {
//            textView.setText("");
//        }

        for (ImageView dotView : mDotViews) {
            dotView.setVisibility(GONE);
        }
        char[] luckNumChar = luckNum.toCharArray();
        LogUtils.eTag("inputValue_Pay", luckNum + ":::" + GsonUtils.toJson(luckNumChar));
        for (int i = 0; i < luckNumChar.length; i++) {
//            mRlViews[i].setText(luckNumChar[i] + "");
            mDotViews[i].setVisibility(VISIBLE);
        }

    }
}
