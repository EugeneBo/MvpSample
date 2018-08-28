package com.github.parfenovvs.mvpsample.fragment;

import com.github.parfenovvs.mvpsample.base.BasePresenter;
import com.github.parfenovvs.mvpsample.model.DataLoadedCallback;
import com.github.parfenovvs.mvpsample.model.DetailsService;

import java.io.Serializable;

public class DetailsPresenter extends BasePresenter<DetailsView> implements Serializable{

    private DetailsService detailsService;
    private String itemName;


    public DetailsPresenter() {
        detailsService = DetailsService.getInstance();
    }


    @Override
    protected void onAttach() {
        super.onAttach();
        getView().showProgress();
        detailsService.setItemName(itemName);
        detailsService.loadItemDescription(new DataLoadedCallback() {
            @Override
            public void onDataLoaded(String itemName, String description) {
                if (getView() != null)  getView().showData(itemName, description);
            }
        });
    }

    @Override
    protected void onDetach() {

    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }



}
