package com.scott.demo.mvp.contract;

import com.scott.lib.base.mvp.IBasePresenter;
import com.scott.lib.base.mvp.IBaseView;

/**
 * Created by scott_he on 16/10/17.
 */

public interface TestContract {
    interface View extends IBaseView<Presenter> {

    }

    interface Presenter extends IBasePresenter {
        public void showData();
    }
}
