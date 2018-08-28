package com.github.parfenovvs.mvpsample.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.github.parfenovvs.mvpsample.R;
import com.github.parfenovvs.mvpsample.base.BasePresenter;
import com.github.parfenovvs.mvpsample.base.BaseView;

public class DetailsFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements DetailsView {

    private TextView headerTextView;
    private ScrollView scrollView;
    private TextView descriptionTextView;
    private ProgressBar progressBar;

    private DetailsPresenter detailsPresenter;

    private Handler handler = new Handler();

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            detailsPresenter = (DetailsPresenter) savedInstanceState.getSerializable("detailsPresenter");
        } else detailsPresenter = new DetailsPresenter();


        detailsPresenter.setItemName(getArguments().getString("clickedItemName"));

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        scrollView = view.findViewById(R.id.descriptionScrollView);
        headerTextView = view.findViewById(R.id.headerTextView);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        progressBar = view.findViewById(R.id.progressBarDetails);

        return view;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onStart() {
        super.onStart();
        detailsPresenter.attachView(this);
    }

    @Override
    public void showProgress() {

        headerTextView.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(String itemName, String description) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                headerTextView.setText(itemName);
                descriptionTextView.setText(description);

                progressBar.setVisibility(View.GONE);

                headerTextView.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onDestroy() {
        detailsPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("detailsPresenter", detailsPresenter);
    }


}
