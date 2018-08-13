package com.github.parfenovvs.mvpsample.mainscreen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.github.parfenovvs.mvpsample.R;
import com.github.parfenovvs.mvpsample.base.BaseActivity;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

  private EditText editText;
  private Button button;
  private TextView resultTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editText = findViewById(R.id.editText);
    button = findViewById(R.id.button);
    resultTextView = findViewById(R.id.resultTextView);

    button.setOnClickListener(v -> getPresenter().onButtonClicked(editText.getText().toString()));
  }

  @Override
  protected MainPresenter providePresenter() {
    return new MainPresenter();
  }

  @Override
  public void showResult(String result) {
    resultTextView.setText(result);
  }
}
