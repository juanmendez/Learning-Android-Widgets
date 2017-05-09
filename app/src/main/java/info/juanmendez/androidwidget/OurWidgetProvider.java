package info.juanmendez.androidwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import java.util.Random;
import timber.log.Timber;

/**
 * Created by Juan Mendez on 5/8/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
public class OurWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) {

            updateWidget(context,
                    AppWidgetManager.getInstance(context),
                    intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1),
                    intent.getIntExtra(MainActivity.RANDOM_INT, 0 ));
        }
        else {
            super.onReceive(context, intent);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Timber.i( "on update" );
        final int count = appWidgetIds.length;

        for (int i = 0; i < count; i++) {
            updateWidget( context, appWidgetManager, appWidgetIds[i], 0 );
        }
    }


    private void updateWidget( Context ctxt, AppWidgetManager mgr, int appWidgetId, int randomInt ){

        if( appWidgetId > 0 ){

            if( randomInt < 1 )
                randomInt = (new Random().nextInt(900) + 100);

            String randomNumber = String.format("%03d", randomInt );

            RemoteViews updateViews = new RemoteViews(ctxt.getPackageName(), R.layout.widget_demo);
            updateViews.setTextViewText(R.id.textView, randomNumber);
            Timber.i( appWidgetId + ": change for textview " + randomNumber );

            Intent intent = new Intent(ctxt, OurWidgetProvider.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId );

            PendingIntent pendingIntent = PendingIntent.getBroadcast(ctxt,
                    appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            updateViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent);
            mgr.updateAppWidget(appWidgetId, updateViews);
        }
    }
}