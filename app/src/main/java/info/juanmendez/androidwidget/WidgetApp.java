package info.juanmendez.androidwidget;

import android.app.Application;

import info.juanmendez.androidwidget.module.AppComponent;
import info.juanmendez.androidwidget.module.AppModule;
import info.juanmendez.androidwidget.module.DaggerAppComponent;
import timber.log.Timber;


/**
 * Created by Juan Mendez on 5/8/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class WidgetApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if( appComponent == null ){
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
