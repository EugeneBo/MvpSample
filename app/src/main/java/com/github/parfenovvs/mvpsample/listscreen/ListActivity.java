package com.github.parfenovvs.mvpsample.listscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.FrameLayout;

import com.github.parfenovvs.mvpsample.R;
import com.github.parfenovvs.mvpsample.base.BaseActivity;
import com.github.parfenovvs.mvpsample.fragment.ListFragment;

import java.util.List;

public class ListActivity extends BaseActivity<ListView, ListPresenter> implements ListView {

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        fragmentContainer = findViewById(R.id.fragmentContainer);

        if (savedInstanceState == null) {
            Log.d("ListActivity", "savedInstanceState == null");
            startFragment();
        } else  Log.d("ListActivity", "savedInstanceState != null");
    }

    @Override
    protected ListPresenter providePresenter() {
        return new ListPresenter();
    }

    public void startFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ListFragment listFragment = new ListFragment<ListView, ListPresenter>();
        fragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, listFragment)
                .commit();
    }


    @Override
    public void showProgress() {
        //убрать!
    }

    @Override
    public void showData(List<String> data) {
        //убрать!

    }
}
