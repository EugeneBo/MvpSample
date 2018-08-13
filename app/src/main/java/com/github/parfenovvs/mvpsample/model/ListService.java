package com.github.parfenovvs.mvpsample.model;

import java.util.ArrayList;
import java.util.List;

public class ListService {
  private static final ListService INSTANCE = new ListService();

  private ListService() {}

  public static ListService getInstance() {
    return INSTANCE;
  }

  public void loadData(final Callback<List<String>> callback) {
    new Thread(() -> {
      try {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
          list.add("Item " + i);
        }
        Thread.sleep(3000L);
        callback.onSuccess(list);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    })
        .start();
  }

}
