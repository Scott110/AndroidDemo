package com.scott.lib.base.mvvm;

/**
 * Created by scott_he on 16/10/17.
 */

public abstract class IBaseViewModle<V extends IBaseView> {

    private V controllerView;

    public IBaseViewModle(IBaseView controllerView) {
        this.controllerView = (V) controllerView;
    }

    public V getControllerView() {
        return controllerView;
    }
}
