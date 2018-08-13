package com.github.parfenovvs.mvpsample.mainscreen;

import com.github.parfenovvs.mvpsample.base.BasePresenter;
import com.github.parfenovvs.mvpsample.model.SentencesService;

public class MainPresenter extends BasePresenter<MainView> {
  private SentencesService service;

  public MainPresenter() {
    this.service = SentencesService.getInstance();
  }

  @Override
  protected void onAttach() {
    //service.loadList()
  }

  @Override
  protected void onDetach() {
    //service.stopAllLoads()
  }

  public void onButtonClicked(String sentence) {
    service.getCount(sentence, data -> {
      if (getView() != null) getView().showResult(String.valueOf(data));
    });
  }
}
