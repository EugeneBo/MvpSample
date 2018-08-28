package com.github.parfenovvs.mvpsample.fragment;

import com.github.parfenovvs.mvpsample.base.BaseView;

public interface DetailsView extends BaseView{

    void showProgress();
    void showData(String itemName, String description);

}
