package com.github.parfenovvs.mvpsample.listscreen;

import com.github.parfenovvs.mvpsample.base.BasePresenter;
import com.github.parfenovvs.mvpsample.model.Callback;
import com.github.parfenovvs.mvpsample.model.ListService;

import java.io.Serializable;
import java.util.List;

public class ListPresenter extends BasePresenter<ListView> implements Serializable {

  @Override
  protected void onAttach() {
    getView().showProgress();

    ListService.getInstance().loadData(new Callback<List<String>>() {
      @Override
      public void onSuccess(List<String> data) {
        if (ListPresenter.this.getView() != null) ListPresenter.this.getView().showData(data);
      }
    });

  }

}
