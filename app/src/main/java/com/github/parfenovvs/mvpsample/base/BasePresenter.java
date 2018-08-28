package com.github.parfenovvs.mvpsample.base;

import android.util.Log;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView> {
    private WeakReference<V> viewRef;

    public final void attachView(V view) {
        viewRef = new WeakReference<>(view);
        onAttach();
    }

    public final void detachView() {
        onDetach();
        viewRef.clear();
    Log.d("BasePresenter","detachView");
    }

    protected void onAttach() {
    }

    protected void onDetach() {
    }

    public final V getView() {
        return viewRef.get();
    }
}
