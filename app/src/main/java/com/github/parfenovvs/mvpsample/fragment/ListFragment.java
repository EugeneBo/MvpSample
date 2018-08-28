package com.github.parfenovvs.mvpsample.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.parfenovvs.mvpsample.R;
import com.github.parfenovvs.mvpsample.base.BasePresenter;
import com.github.parfenovvs.mvpsample.base.BaseView;
import com.github.parfenovvs.mvpsample.listscreen.ListPresenter;
import com.github.parfenovvs.mvpsample.listscreen.ListView;


import java.util.List;

public class ListFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements ListView {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private Handler handler = new Handler();
    private SampleAdapter adapter = new SampleAdapter();
    private ListPresenter listPresenter;

    private static int counter = 0;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            listPresenter = (ListPresenter) savedInstanceState.getSerializable("listPresenter");
        } else {
            listPresenter = new ListPresenter();
            counter++;
            Log.d("ListActivity", "\t\t\t\tCounter: " + counter);
        }

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBarList);

        recyclerView.setAdapter(adapter);

        return view;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onStart() {
        super.onStart();
        listPresenter.attachView(this);
    }

    @Override
    public void showProgress() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(List<String> data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.setItems(data);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroy() {
        listPresenter.detachView();
        super.onDestroy();
    }

    private void attachPresenter() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("listPresenter", listPresenter);
    }

    class SampleAdapter extends RecyclerView.Adapter<VH> {
        private List<String> items;

        void setItems(List<String> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            if (items == null) return 0;
            return items.size();
        }
    }

    class VH extends RecyclerView.ViewHolder {
        private TextView textView;
        private String itemName;


        VH(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putString("clickedItemName", itemName);

                    Fragment detailsFragment = new DetailsFragment<DetailsView, DetailsPresenter>();
                    detailsFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    if (fragmentManager != null) {
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainer, detailsFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });
        }

        void bind(String text) {
            this.itemName = text;
            textView.setText(text);
        }
    }
}

