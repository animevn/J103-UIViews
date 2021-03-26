package com.haanhgs.app.uiviews.viewmodel;

import com.haanhgs.app.uiviews.model.Model;
import com.haanhgs.app.uiviews.model.Repo;
import androidx.lifecycle.LiveData;

public class MyViewModel extends androidx.lifecycle.ViewModel {

    private final Repo repo = new Repo();

    public LiveData<Model> getModel(){
        return repo.getLiveData();
    }

    public void setAlpha(float alpha){
        repo.setAlpha(alpha);
    }

    public void setScale(float scale){
        repo.setScale(scale);
    }

    public void setFilter(int filter){
        repo.setFilter(filter);
    }

    public void setTimezone(String timezone){
        repo.setTimezone(timezone);
    }

    public void setShow(boolean show){
        repo.setShow(show);
    }

    public void setString(String string){
        repo.setString(string);
    }

}
