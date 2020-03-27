package com.haanhgs.app.uiviews.model;

import androidx.lifecycle.MutableLiveData;

public class Repo {

    private MutableLiveData<Model>liveData = new MutableLiveData<>();
    private Model model = new Model();

    public Repo() {
        liveData.setValue(model);
    }

    public MutableLiveData<Model> getLiveData() {
        return liveData;
    }

    public void setAlpha(float alpha){
        model.setAlpha(alpha);
        liveData.setValue(model);
    }

    public void setScale(float scale){
        model.setScale(scale);
        liveData.setValue(model);
    }

    public void setFilter(int filter){
        model.setFilter(filter);
        liveData.setValue(model);
    }

    public void setTimezone(String timezone){
        model.setTimezone(timezone);
        liveData.setValue(model);
    }

    public void setShow(boolean show){
        model.setShow(show);
        liveData.setValue(model);
    }

    public void setString(String string){
        model.setString(string);
        liveData.setValue(model);
    }

}
