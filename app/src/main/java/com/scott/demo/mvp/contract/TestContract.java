package com.scott.demo.mvp.contract;

import com.scott.lib.base.mvp.BasePresenter;
import com.scott.lib.base.mvp.BaseView;

/**
 * Created by scott_he on 16/10/17.
 */

public interface TestContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        public void showData();
    }
}
