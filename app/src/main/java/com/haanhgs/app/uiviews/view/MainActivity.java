package com.haanhgs.app.uiviews.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextClock;
import com.haanhgs.app.uiviews.R;
import com.haanhgs.app.uiviews.model.Model;
import com.haanhgs.app.uiviews.viewmodel.MyViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cbTransparent)
    CheckBox cbTransparent;
    @BindView(R.id.cbTint)
    CheckBox cbTint;
    @BindView(R.id.cbResize)
    CheckBox cbResize;
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.rgLocation)
    RadioGroup rgLocation;
    @BindView(R.id.etLocation)
    EditText etLocation;
    @BindView(R.id.bnLocation)
    Button bnLocation;
    @BindView(R.id.tcClock)
    TextClock tcClock;
    @BindView(R.id.swToggleWeb)
    Switch swToggleWeb;
    @BindView(R.id.wvWeb)
    WebView wvWeb;

    private MyViewModel viewModel;

    private void showFullScreen(){
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        viewModel.getModel().observe(this, model -> {
            tcClock.setTimeZone(model.getTimezone());
            ivImage.setAlpha(model.getAlpha());
            ivImage.setScaleX(model.getScale());
            ivImage.setScaleY(model.getScale());
            ivImage.setColorFilter(model.getFilter());
            wvWeb.setVisibility(model.isShow() ? View.VISIBLE : View.INVISIBLE);
            bnLocation.setText(model.getString());
        });
    }

    private void handleCheckBoxTransparent(){
        cbTransparent.setOnCheckedChangeListener((buttonView, isChecked) ->
                viewModel.setAlpha(isChecked ? 0.3f : 1f));
    }

    private void handleCheckBoxTint(){
        cbTint.setOnCheckedChangeListener((buttonView, isChecked) ->
            viewModel.setFilter(isChecked ? Color.argb(128, 255, 255, 0) : Color.argb(0, 0, 0, 0)));
    }

    private void handleCheckBoxResize(){
        cbResize.setOnCheckedChangeListener((buttonView, isChecked) ->
                viewModel.setScale(isChecked ? 0.5f : 1f));
    }

    private void toggleRadioGroupLocation(){
        rgLocation.check(R.id.rbnSaigon);
        rgLocation.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rbnBerlin:
                    viewModel.setTimezone(Model.Berlin);
                    break;
                case R.id.rbnParis:
                    viewModel.setTimezone(Model.Paris);
                    break;
                case R.id.rbnSaigon:
                    viewModel.setTimezone(Model.Saigon);
                    break;
            }
        });
    }

    //enable javascript
    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView(){
        swToggleWeb.setChecked(true);
        wvWeb.getSettings().setJavaScriptEnabled(true);
        wvWeb.loadUrl("https://www.quora.com/");
    }

    private void handleSwitchWebView(){
        swToggleWeb.setOnCheckedChangeListener((buttonView, isChecked)
                -> viewModel.setShow(isChecked));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showFullScreen();
        initViewModel();
        handleCheckBoxTransparent();
        handleCheckBoxTint();
        handleCheckBoxResize();
        toggleRadioGroupLocation();
        setupWebView();
        handleSwitchWebView();
    }

    @OnClick(R.id.bnLocation)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(etLocation.getText())){
            viewModel.setString(etLocation.getText().toString());
        }
    }
}
