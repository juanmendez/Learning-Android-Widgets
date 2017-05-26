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

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import info.juanmendez.androidwidget.dependencies.RealmModels;
import info.juanmendez.androidwidget.models.Icon;
import timber.log.Timber;

/**
 * Created by Juan Mendez on 5/8/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
public class IconWidgetProvider extends AppWidgetProvider {

    public static String ICON_PICKED = "info.juanmendez.android.appwidget.ICON_PICKED";

    @Inject
    RealmModels realmModels;

    public IconWidgetProvider() {
        Timber.i( "constructor");
        WidgetApp.getAppComponent().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Timber.i( "on receive: " + intent.getAction() );
        if (intent.getAction() == null ) {

            int[] widget_ids = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

            for( int widget_id: widget_ids){
                updateWidget( context, AppWidgetManager.getInstance(context), widget_id, intent );
            }
        }else{
            super.onReceive(context, intent);
        }
    }


    @Override
    public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Icon icon = realmModels.getIcon();
        Intent dummyIntent = new Intent();

        //dummy intent is used to pass to updateWidget the icon stored in the database.
        if( icon != null ){
            dummyIntent.putExtra(ICON_PICKED, icon.getIconId() );
        }

        for( int i = 0; i < appWidgetIds.length; i++ ) {
            updateWidget( ctxt, appWidgetManager, appWidgetIds[i], dummyIntent );
        }

        super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
    }

    private void updateWidget( Context ctxt, AppWidgetManager appWidgetManager, int appWidgetId, Intent intentReceived ){

        Intent i;
        PendingIntent pi;

        HashMap<Integer, FontAwesome.Icon> iconMap = new HashMap<>();
        iconMap.put( R.id.androidBtn, FontAwesome.Icon.faw_android );
        iconMap.put( R.id.appleBtn, FontAwesome.Icon.faw_apple );
        iconMap.put( R.id.linuxBtn, FontAwesome.Icon.faw_linux );
        iconMap.put( R.id.windowBtn, FontAwesome.Icon.faw_windows );


        int iconSelected = intentReceived!=null?intentReceived.getIntExtra(ICON_PICKED, 0 ):0;
        int currentColor;

        if( appWidgetId > 0 ){

            RemoteViews widget = new RemoteViews(ctxt.getPackageName(), R.layout.widget_demo);

            for (Map.Entry<Integer, FontAwesome.Icon> entry: iconMap.entrySet() ){

                i = new Intent( ctxt, IconService.class );
                i.putExtra( ICON_PICKED, entry.getKey() );
                i.setAction("Icon_Selected_" + entry.getKey() + "_" + + System.currentTimeMillis() );

                pi = PendingIntent.getService(ctxt, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);


                Timber.i( "lets see if its able to select");
                Timber.i( entry.getKey() + " " + iconSelected + " equals?" + (entry.getKey() == iconSelected?"yes":"no")  );
                currentColor = entry.getKey() == iconSelected? Color.GRAY: Color.BLACK;

                IconicsDrawable iconsDrawable = new IconicsDrawable(ctxt)
                        .icon(entry.getValue())
                        .color(currentColor)
                        .sizeDp(24);

                widget.setImageViewBitmap( entry.getKey(),iconsDrawable.toBitmap() );
                widget.setOnClickPendingIntent( entry.getKey(), pi);
            }

            appWidgetManager.updateAppWidget(appWidgetId, widget);
        }
    }
}