package com.github.parfenovvs.mvpsample.listscreen;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.github.parfenovvs.mvpsample.R;
import com.github.parfenovvs.mvpsample.base.BaseActivity;
import java.util.List;

public class ListActivity extends BaseActivity<ListView, ListPresenter> implements ListView {
  private RecyclerView recyclerView;
  private ProgressBar progressBar;
  private Handler handler = new Handler();

  private SampleAdapter adapter = new SampleAdapter();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    progressBar = findViewById(R.id.progressBar);
    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setAdapter(adapter);
  }

  @Override
  protected ListPresenter providePresenter() {
    return new ListPresenter();
  }

  @Override
  public void showProgress() {
    recyclerView.setVisibility(View.GONE);
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void showData(List<String> data) {
    handler.post(() -> {
      adapter.setItems(data);
      recyclerView.setVisibility(View.VISIBLE);
      progressBar.setVisibility(View.GONE);
    });
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

    VH(View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.textView);
    }

    void bind(String text) {
      textView.setText(text);
    }
  }
}
