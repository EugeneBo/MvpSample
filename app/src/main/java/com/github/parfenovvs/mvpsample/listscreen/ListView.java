package com.github.parfenovvs.mvpsample.listscreen;

import com.github.parfenovvs.mvpsample.base.BaseView;
import java.util.List;

public interface ListView extends BaseView {
  void showProgress();
  void showData(List<String> data);
}
