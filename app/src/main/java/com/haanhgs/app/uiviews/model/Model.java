package com.haanhgs.app.uiviews.model;

import android.graphics.Color;

public class Model {

    public static final String Paris = "Europe/Paris";
    public static final String Berlin = "Europe/Berlin";
    public static final String Saigon = "Asia/Ho_Chi_Minh";

    private float alpha = 1f;
    private int filter = Color.argb(0, 0, 0, 0);
    private float scale = 1f;
    private String timezone = Saigon;
    private boolean show = true;
    private String string = "";

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
