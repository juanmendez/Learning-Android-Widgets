package info.juanmendez.androidwidget;

import android.app.Application;

import timber.log.Timber;


/**
 * Created by Juan Mendez on 5/8/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class WidgetApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
