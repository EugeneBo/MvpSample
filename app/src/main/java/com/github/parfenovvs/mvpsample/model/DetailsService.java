package com.github.parfenovvs.mvpsample.model;

public class DetailsService {

    private final static DetailsService INSTANCE = new DetailsService();
    private String itemName;

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public static DetailsService getInstance() {
        return INSTANCE;
    }

    public void loadItemDescription(final DataLoadedCallback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder result = new StringBuilder();
                    Thread.sleep(500);
                    for (int i = 0; i < 50; i++) {
                        result.append("All work and no play makes Jack a dull boy. ");
                    }
                    callback.onDataLoaded(itemName, result.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
