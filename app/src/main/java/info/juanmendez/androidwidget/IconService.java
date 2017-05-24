package info.juanmendez.androidwidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

import javax.inject.Inject;

import info.juanmendez.androidwidget.dependencies.RealmProvider;
import timber.log.Timber;


/**
 * Created by Juan Mendez on 5/23/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class IconService extends Service {

    public static final String COLOR = "color";

    public static int[] colors = {Color.RED, Color.MAGENTA, Color.BLUE, Color.GREEN};


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

        Timber.i( "I can start realm if I really want", realmProvider.getRealm().getPath() );

        ComponentName componentName = new ComponentName( this, OurWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] widgetIds = appWidgetManager.getAppWidgetIds(componentName);

        Intent sendIntent = new Intent( this, OurWidgetProvider.class );
        sendIntent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_IDS, widgetIds );
        sendIntent.putExtra( COLOR, colors[(new Random()).nextInt(3)]);
        sendBroadcast( sendIntent );


        return START_REDELIVER_INTENT;
    }
}
