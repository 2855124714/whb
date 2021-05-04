package com.fjsy.architecture.ui.xpopup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.PopupPayPassordBinding;
import com.fjsy.architecture.ui.OnNumberKeyboardInputListener;
import com.lxj.xpopup.impl.FullScreenPopupView;

public class PayPasswordPopupView extends FullScreenPopupView implements OnNumberKeyboardInputListener {

    private Context context;
    private ObservableField<String> moneyValue = new ObservableField<>();
    private ObservableField<String> redEnvelopeType = new ObservableField<>();
    private PopupPayPassordBinding mBinding;
    private CompeleteListener compeleteListener;

    public PayPasswordPopupView(@NonNull Context context, String value, String type) {
        super(context);
        this.context = context;
        moneyValue.set(value);
        redEnvelopeType.set(type);
    }

    @Override
    public void onInputValue(String inputValue, boolean complete) {
        mBinding.numInputResult.updateInputNum(inputValue);
        if (complete) {
            if (null != compeleteListener) {
                compeleteListener.compelete(inputValue);
            }
        }


    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_pay_passord;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mBinding = DataBindingUtil.bind(getPopupImplView());
        if (null != mBinding) {
            mBinding.money.setText(String.format(context.getString(R.string.moneyStr), moneyValue.get()));
            mBinding.type.setText(redEnvelopeType.get());
            mBinding.back.setOnClickListener(view -> dismiss());
            mBinding.numberKeyboardInput.setOnNumberKeyboardInputListener(this);
            mBinding.numberKeyboardInput.setKeyNum(4);
            mBinding.linearPayMethod.setOnClickListener(view -> {
                //todo 选择支付的方式，不知道要不要扩展
            });
        }
    }

    public void setCompeleteListener(CompeleteListener listener) {
        this.compeleteListener = listener;
    }

    public interface CompeleteListener {
        void compelete(String value);
    }
}
