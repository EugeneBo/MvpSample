package com.github.parfenovvs.mvpsample.listscreen;

import com.github.parfenovvs.mvpsample.base.BasePresenter;
import com.github.parfenovvs.mvpsample.model.ListService;

public class ListPresenter extends BasePresenter<ListView> {

  @Override
  protected void onAttach() {
    getView().showProgress();
    ListService.getInstance().loadData(data -> {
      if (getView() != null) getView().showData(data);
    });
  }
}
