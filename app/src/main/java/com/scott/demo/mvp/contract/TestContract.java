package com.scott.demo.mvp.contract;

import com.scott.lib.base.mvvm.IBaseViewModle;
import com.scott.lib.base.mvvm.IBaseView;

/**
 * Created by scott_he on 16/10/17.
 */

public interface TestContract {
    interface View extends IBaseView<Presenter> {

    }

    interface Presenter extends IBaseViewModle {
        public void showData();
    }
}
