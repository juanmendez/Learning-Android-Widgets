package info.juanmendez.androidwidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import info.juanmendez.androidwidget.dependencies.RealmProvider;
import timber.log.Timber;


/**
 * Created by Juan Mendez on 5/23/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class IconService extends Service {


    @Inject
    RealmProvider realmProvider;

    public IconService() {
        WidgetApp.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Timber.i( "iconService");

        int iconSelected = intent.getIntExtra( IconWidgetProvider.ICON_PICKED, 0 );

        if( iconSelected != 0 ){

            ComponentName componentName = new ComponentName( this, IconWidgetProvider.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] widgetIds = appWidgetManager.getAppWidgetIds(componentName);


            Intent sendIntent = new Intent( this, IconWidgetProvider.class );
            sendIntent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_IDS, widgetIds );
            sendIntent.putExtra( IconWidgetProvider.ICON_PICKED, iconSelected );
            sendBroadcast( sendIntent );
        }



        return START_REDELIVER_INTENT;
    }
}
