package com.haanhgs.app.uidemomore;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showFullScreen();
        setPortraitMode();
    }

    @OnClick(R.id.bnLocation)
    public void onViewClicked() {
    }
}
