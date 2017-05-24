package info.juanmendez.androidwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

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


        if (intent.getAction() == null ) {

            int color = intent.getIntExtra( IconService.COLOR, 0 );
            Timber.i( "WidgetProvider.onReceived " + color );

            int[] widget_ids = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

            for( int widget_id: widget_ids){
                updateWidget( context, AppWidgetManager.getInstance(context), widget_id, color );
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
            updateWidget( ctxt, appWidgetManager, appWidgetIds[i], 0);
        }

        super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
    }

    private void updateWidget( Context ctxt, AppWidgetManager appWidgetManager, int appWidgetId, int tintColor ){

        Timber.i( "update with color " + tintColor );
        Intent serviceIntent, clickIntent;
        PendingIntent clickPI;

        FavCountry favCountry = realmModels.getFavoriteCountry();

        if( appWidgetId > 0 ){

            RemoteViews widget = new RemoteViews(ctxt.getPackageName(), R.layout.widget_demo);

            Intent i = new Intent( ctxt, IconService.class );
            PendingIntent pi = PendingIntent.getService(ctxt, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

            IconicsDrawable iconsDrawable = new IconicsDrawable(ctxt)
                    .icon(FontAwesome.Icon.faw_android)
                    .color(tintColor!=0?tintColor:Color.BLACK)
                    .sizeDp(24);

            widget.setImageViewBitmap( R.id.walkingButton,iconsDrawable.toBitmap() );
            widget.setOnClickPendingIntent( R.id.walkingButton, pi);


            appWidgetManager.updateAppWidget(appWidgetId, widget);
        }
    }


}