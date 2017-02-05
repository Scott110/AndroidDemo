package com.scott.demo.simple.ui;

import com.scott.demo.bean.Person;
import com.scott.lib.base.mvvm.IBaseView;
import com.scott.lib.base.mvvm.IBaseViewModle;
import com.scott.lib.base.mvvm.IBaseViewModleContract;
import com.scott.libhttp.api.BaseApi;

/**
 * author: heshantao
 * data: 2017/2/5.
 */

public interface DiVMContract extends IBaseViewModleContract {
    interface View<T> extends IBaseView {
        void updateUI();
    }


    abstract class ViewModule extends IBaseViewModle<View> {

        public ViewModule(IBaseView controllerView) {
            super(controllerView);
        }

        public abstract void requestPersonInfo(BaseApi api);

    }
}
