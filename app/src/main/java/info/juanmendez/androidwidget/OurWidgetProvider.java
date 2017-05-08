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
 *
 * Upon updating one widget, you get all other instance display the same string value
 */
public class OurWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Timber.i( "on update" );
        final int count = appWidgetIds.length;

        int randomInt = (new Random().nextInt(900) + 100);
        for (int i = 0; i < count; i++) {
            updateWidget( context, appWidgetManager, appWidgetIds[i], appWidgetIds, randomInt );
        }
    }

    private void updateWidget( Context ctxt, AppWidgetManager mgr, int appWidgetId, int[] appWidgetIds, int randomInt ){

        String randomString = String.format("%03d", randomInt );

        Timber.i( appWidgetId + ": change for textview " + randomString );
        RemoteViews updateViews = new RemoteViews(ctxt.getPackageName(), R.layout.widget_demo);
        updateViews.setTextViewText(R.id.textView, randomString);

        Intent intent = new Intent(ctxt, OurWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds );

        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctxt,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        updateViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent);

        mgr.updateAppWidget(appWidgetId, updateViews);
    }
}
