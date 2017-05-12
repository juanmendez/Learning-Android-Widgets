package info.juanmendez.androidwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Created by Juan Mendez on 5/8/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
public class OurWidgetProvider extends AppWidgetProvider {

    public static String COUNTRY_PICKED = "info.juanmendez.android.appwidget.COUNTRY_PICKED";


    @Override
    public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for( int i = 0; i < appWidgetIds.length; i++ ) {
            updateWidget( ctxt, appWidgetManager, appWidgetIds[i]);
        }

        super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
    }

    private void updateWidget( Context ctxt, AppWidgetManager appWidgetManager, int appWidgetId ){

        Intent serviceIntent, clickIntent;
        PendingIntent clickPI;

        if( appWidgetId > 0 ){

            clickIntent = new Intent(ctxt, MainActivity.class);
            clickPI = PendingIntent.getActivity(ctxt, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            serviceIntent = new Intent(ctxt, WidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews widget = new RemoteViews(ctxt.getPackageName(), R.layout.widget_demo);
            widget.setRemoteAdapter(R.id.listView, serviceIntent);
            widget.setPendingIntentTemplate(R.id.listView, clickPI);

            appWidgetManager.updateAppWidget(appWidgetId, widget);
        }
    }
}