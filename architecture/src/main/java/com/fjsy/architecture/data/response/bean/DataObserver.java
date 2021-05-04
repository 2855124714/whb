package com.fjsy.architecture.data.response.bean;

import androidx.annotation.NonNull;

public abstract class DataObserver<T> {
    private DataListener dataListener;

    protected DataObserver(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    protected void dataStart() {
        if (this.dataListener != null) {
            this.dataListener.dataStart();
        }
    }

    protected void dataStop() {
        if (this.dataListener != null) {
            this.dataListener.dataStop();
        }
    }

    final void dataSuccess(@NonNull T resultBean) {
        if (resultBean instanceof BaseBean) {
            BaseBean baseBean = (BaseBean) resultBean;
            dataResult(baseBean.statusInfo, resultBean);
            if (baseBean.statusInfo.isOther()) {
                if (this.dataListener != null)
                    this.dataListener.dataOther(baseBean.statusInfo);
            }
        } else {
            dataResult(new StatusInfo(StatusInfo.STATUS_SUCCESS), resultBean);
        }
    }

    protected void dataError(Throwable throwable) {
        dataResult(new StatusInfo(StatusInfo.Error), (T) throwable);
    }

    protected abstract void dataResult(StatusInfo statusInfo, T bean);
}
