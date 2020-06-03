package com.example.purplrcode;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private SavedStateHandle handle;
    private String keyName = getApplication().getResources().getString(R.string.MY_KEY_NAME);
    private String keyClass = getApplication().getResources().getString(R.string.MY_KEY_CLASS);
    private String keyStu = getApplication().getResources().getString(R.string.MY_KEY_STU);
    private String keyUri = "My_Key_Uri";
    private String shpName = getApplication().getResources().getString(R.string.MY_DATA);
    Resources r = getApplication().getResources();
    private String rem = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
            + r.getResourcePackageName(R.drawable.portrait) + "/"
            + r.getResourceTypeName(R.drawable.portrait) + "/"
            + r.getResourceEntryName(R.drawable.portrait);


    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if (!handle.contains(keyStu) & !handle.contains(keyName) & !handle.contains(keyClass) & !handle.contains(keyUri)) {
            SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
            handle.set(keyName,shp.getString(keyName,"52486 雷姆"));
            handle.set(keyClass,shp.getString(keyClass,"露格尼卡/罗兹瓦尔宅邸"));
            handle.set(keyStu,shp.getString(keyStu,"本科生"));
            handle.set(keyUri,shp.getString(keyUri,rem));
        }

    }
    public LiveData<String> getMyName() {
        return handle.getLiveData(keyName);
    }
    public LiveData<String> getMyClass() {
        return handle.getLiveData(keyClass);
    }
    public LiveData<String> getStu() {
        return handle.getLiveData(keyStu);
    }
    public LiveData<String> getUri() {
        return handle.getLiveData(keyUri);
    }

    void save() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =shp.edit();
        editor.putString(keyName,getMyName().getValue());
        editor.putString(keyClass,getMyClass().getValue());
        editor.putString(keyStu,getStu().getValue());
        editor.putString(keyUri,getUri().getValue());
        editor.apply();
    }
    public void submit(String name, String gradeClass, String stu) {
        handle.set(keyName,name);
        handle.set(keyClass,gradeClass);
        handle.set(keyStu,stu);
    }
    public void setUri(Uri uri) {
        String str = uri.toString();
        handle.set(keyUri,str);
    }

}