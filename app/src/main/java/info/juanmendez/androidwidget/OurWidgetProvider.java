package info.juanmendez.androidwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import javax.inject.Inject;

import info.juanmendez.androidwidget.dependencies.RealmModels;
import info.juanmendez.androidwidget.models.FavCountry;
import timber.log.Timber;

/**
 * Created by Juan Mendez on 5/8/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
public class OurWidgetProvider extends AppWidgetProvider {

    public static String COUNTRY_PICKED = "info.juanmendez.android.appwidget.COUNTRY_PICKED";

    @Inject
    RealmModels realmModels;

    public OurWidgetProvider() {
        Timber.i( "constructor");
        WidgetApp.getAppComponent().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Timber.i( "WidgetProvider.onReceived ");
        if (intent.getAction() == null ) {

            int[] widget_ids = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

            for( int widget_id: widget_ids){
                updateWidget( context, AppWidgetManager.getInstance(context), widget_id );
            }
        }
        else {
            super.onReceive(context, intent);
            Timber.i( intent.getAction() );
        }
    }


    @Override
    public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Timber.i( "widgetProvider.onUpdate");

        for( int i = 0; i < appWidgetIds.length; i++ ) {
            updateWidget( ctxt, appWidgetManager, appWidgetIds[i]);
        }

        super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
    }

    private void updateWidget( Context ctxt, AppWidgetManager appWidgetManager, int appWidgetId ){

        Intent serviceIntent, clickIntent;
        PendingIntent clickPI;

        FavCountry favCountry = realmModels.getFavoriteCountry();

        if( appWidgetId > 0 ){

            clickIntent = new Intent(ctxt, MainActivity.class);
            clickPI = PendingIntent.getActivity(ctxt, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            serviceIntent = new Intent(ctxt, WidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews widget = new RemoteViews(ctxt.getPackageName(), R.layout.widget_demo);

            widget.setRemoteAdapter(R.id.listView, serviceIntent);
            widget.setPendingIntentTemplate(R.id.listView, clickPI);

            if( favCountry != null ){
                widget.setTextViewText(R.id.favoriteCountry, favCountry.getName() );
            }


            appWidgetManager.updateAppWidget(appWidgetId, widget);
        }
    }
}