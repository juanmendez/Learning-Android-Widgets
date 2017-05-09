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

    public static String EXTRA_WORD= "com.commonsware.android.appwidget.lorem.WORD";

    @Override
    public void onReceive(Context ctxt, Intent intent) {
        super.onReceive(ctxt, intent);
    }

    @Override
    public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for( int i = 0; i < appWidgetIds.length; i++ ) {
            updateWidget( ctxt, appWidgetManager, appWidgetIds[i]);
        }

        super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
    }

    private void updateWidget( Context ctxt, AppWidgetManager appWidgetManager, int appWidgetId ){

        Intent clickIntent = new Intent(ctxt, MainActivity.class);
        PendingIntent clickPI = PendingIntent.getActivity(ctxt, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent serviceIntent;

        if( appWidgetId > 0 ){

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