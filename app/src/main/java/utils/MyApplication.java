package utils;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

public class MyApplication extends Application {

    public static Context mContext;
    private ViewModelProvider.Factory factory;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public MyApplication getApplication(){
        return this;
    }

    public ViewModelProvider.Factory gettViewModelFactory() {
        if (factory == null) {
            factory = ViewModelProvider.AndroidViewModelFactory.getInstance(this);
        }
        return factory;
    }
}
