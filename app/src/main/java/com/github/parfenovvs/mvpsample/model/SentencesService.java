package com.github.parfenovvs.mvpsample.model;

public class SentencesService {
  private static final SentencesService INSTANCE = new SentencesService();

  private SentencesService() {}

  public static SentencesService getInstance() {
    return INSTANCE;
  }

  public void getCount(final String sentence, final Callback<Integer> callback) {
    new Thread(() -> {
      try {
        Thread.sleep(10_000L);
        callback.onSuccess(sentence.split(" ").length);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    })
        .start();
  }

}
