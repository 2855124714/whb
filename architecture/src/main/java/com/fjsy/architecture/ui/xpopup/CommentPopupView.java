package com.fjsy.architecture.ui.xpopup;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.PopupCommentBinding;
import com.fjsy.architecture.ui.base.ClickProxy;
import com.lxj.xpopup.core.BottomPopupView;

/**
 * 评论弹窗
 */
public class CommentPopupView extends BottomPopupView {

    public CommentPopupView(@NonNull Context context) {
        super(context);
    }

    public ObservableField<String> contentStr = new ObservableField<>("");
    public ObservableField<String> userName = new ObservableField<>("");

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_comment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        PopupCommentBinding binding = (PopupCommentBinding) DataBindingUtil.bind(getPopupImplView());
        if (binding != null) {
            binding.setClickProxy(new ClickProxyImp());
            binding.setContent(contentStr);
            binding.setUserName(userName);
            binding.executePendingBindings();
        }
    }

    public CommentPopupView setContentStr(String contentStr) {
        this.contentStr.set(contentStr);
        return this;
    }

    public CommentPopupView setUserName(String userName) {
        this.userName.set(userName);
        return this;
    }

    public class ClickProxyImp extends ClickProxy {
        public void sendContent(){
            if (TextUtils.isEmpty(contentStr.get())){
                return;
            }
            if (commentListener!=null)
                commentListener.sendComment(contentStr.get());
            dismiss();
        }
    }

    private CommentListener commentListener;

    public CommentPopupView setCommentListener(CommentListener commentListener) {
        this.commentListener = commentListener;
        return this;
    }

    public interface CommentListener{
        public void sendComment(String content);
    }
}
