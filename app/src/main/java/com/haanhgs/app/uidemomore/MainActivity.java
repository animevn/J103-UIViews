package com.haanhgs.app.uidemomore;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextClock;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    @BindView(R.id.clLeft)
    ConstraintLayout clLeft;
    @BindView(R.id.rbnSaigon)
    RadioButton rbnSaigon;
    @BindView(R.id.rbnParis)
    RadioButton rbnParis;
    @BindView(R.id.rbnBerlin)
    RadioButton rbnBerlin;
    @BindView(R.id.rgLocation)
    RadioGroup rgLocation;
    @BindView(R.id.etLocation)
    EditText etLocation;
    @BindView(R.id.bnLocation)
    Button bnLocation;
    @BindView(R.id.tcClock)
    TextClock tcClock;
    @BindView(R.id.clRight)
    ConstraintLayout clRight;
    @BindView(R.id.swToggleWeb)
    Switch swToggleWeb;
    @BindView(R.id.wvWeb)
    WebView wvWeb;
    @BindView(R.id.clDown)
    ConstraintLayout clDown;

    private static final String Paris = "Europ/Paris";
    private static final String Berlin = "Europe/Berlin";
    private static final String Saigon = "Asia/Ho_Chi_Minh";

    private void showFullScreen(){
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setPortraitMode(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void handleCheckBoxTransparent(){
        cbTransparent.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                ivImage.setAlpha(0.3f);
            }else {
                ivImage.setAlpha(1f);
            }
        });
    }

    private void handleCheckBoxTint(){
        cbTint.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                ivImage.setColorFilter(Color.argb(128, 255, 255, 0));
            }else {
                ivImage.setColorFilter(Color.argb(0, 0, 0, 0));
            }
        });
    }

    private void handleCheckBoxResize(){
        cbResize.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                ivImage.setScaleX(.5f);
                ivImage.setScaleY(.5f);
            }else {
                ivImage.setScaleX(1f);
                ivImage.setScaleY(1f);
            }
        });
    }

    private void toggleRadioGroupLocation(){
        rgLocation.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rbnBerlin:
                    tcClock.setTimeZone(Berlin);
                    break;
                case R.id.rbnParis:
                    tcClock.setTimeZone(Paris);
                    break;
                case R.id.rbnSaigon:
                    tcClock.setTimeZone(Saigon);
                    break;
            }
        });
    }

    //enable javascript
    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView(){
        swToggleWeb.setChecked(false);
        wvWeb.setVisibility(View.INVISIBLE);
        wvWeb.getSettings().setJavaScriptEnabled(true);
        wvWeb.loadUrl("https://www.quora.com/");
    }

    private void handleSwitchWebView(){
        swToggleWeb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                if (wvWeb.getVisibility() == View.INVISIBLE) wvWeb.setVisibility(View.VISIBLE);
            }else {
                if (wvWeb.getVisibility() == View.VISIBLE) wvWeb.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showFullScreen();
        setPortraitMode();
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
            bnLocation.setText(etLocation.getText().toString());
        }
    }
}
