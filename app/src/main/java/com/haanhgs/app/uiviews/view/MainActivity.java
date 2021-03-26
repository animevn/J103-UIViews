package com.haanhgs.app.uiviews.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import com.haanhgs.app.uiviews.R;
import com.haanhgs.app.uiviews.databinding.ActivityMainBinding;
import com.haanhgs.app.uiviews.model.Model;
import com.haanhgs.app.uiviews.viewmodel.MyViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MyViewModel viewModel;
    private ActivityMainBinding binding;

    @SuppressWarnings("deprecation")
    private void showFullScreen(){
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            final WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null){
                insetsController.hide(WindowInsets.Type.statusBars());
            }
        }else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        viewModel.getModel().observe(this, model -> {
            binding.tcClock.setTimeZone(model.getTimezone());
            binding.ivImage.setAlpha(model.getAlpha());
            binding.ivImage.setScaleX(model.getScale());
            binding.ivImage.setScaleY(model.getScale());
            binding.ivImage.setColorFilter(model.getFilter());
            binding.wvWeb.setVisibility(model.isShow() ? View.VISIBLE : View.INVISIBLE);
            binding.bnLocation.setText(model.getString());
        });
    }

    private void handleCheckBoxTransparent(){
        binding.cbTransparent.setOnCheckedChangeListener((buttonView, isChecked) ->
                viewModel.setAlpha(isChecked ? 0.3f : 1f));
    }

    private void handleCheckBoxTint(){
        binding.cbTint.setOnCheckedChangeListener((buttonView, isChecked) ->
            viewModel.setFilter(isChecked ? Color.argb(128, 255, 255, 0) : Color.argb(0, 0, 0, 0)));
    }

    private void handleCheckBoxResize(){
        binding.cbResize.setOnCheckedChangeListener((buttonView, isChecked) ->
                viewModel.setScale(isChecked ? 0.5f : 1f));
    }

    private void toggleRadioGroupLocation(){
        binding.rgLocation.check(R.id.rbnSaigon);
        binding.rgLocation.setOnCheckedChangeListener((group, checkedId) -> {
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
        binding.swToggleWeb.setChecked(true);
//        WebSettings webSettings = binding.wvWeb.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        binding.wvWeb.loadUrl("https://whyshop.org");
    }

    private void handleSwitchWebView(){
        binding.swToggleWeb.setOnCheckedChangeListener((buttonView, isChecked)
                -> viewModel.setShow(isChecked));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ButterKnife.bind(this);
        showFullScreen();
        initViewModel();
        handleCheckBoxTransparent();
        handleCheckBoxTint();
        handleCheckBoxResize();
        toggleRadioGroupLocation();
        setupWebView();
        handleSwitchWebView();
        binding.bnLocation.setOnClickListener(view->onLocationClick());
    }

    public void onLocationClick() {
        if (!TextUtils.isEmpty(binding.etLocation.getText())){
            viewModel.setString(binding.etLocation.getText().toString());
        }
    }
}
