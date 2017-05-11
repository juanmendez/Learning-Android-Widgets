package info.juanmendez.androidwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Juan Mendez on 5/8/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class WidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context ctxt;
    private int widgetId;

    @Inject
    ArrayList<String> thoseItems;

    public WidgetViewsFactory(Context ctxt, Intent intent) {
        this.ctxt = ctxt;
        WidgetApp.getAppComponent().inject( this );

        widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        Timber.i("onCreate");
    }

    @Override
    public void onDataSetChanged() {
        Timber.i("onDataSetChanged");
    }

    @Override
    public void onDestroy() {
        Timber.i("onDestroy");
    }

    @Override
    public int getCount() {
        return thoseItems.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews row=
                new RemoteViews(ctxt.getPackageName(), R.layout.row);

        row.setTextViewText(android.R.id.text1, thoseItems.get(i));

        Intent intent=new Intent();
        Bundle extras=new Bundle();

        extras.putString(OurWidgetProvider.EXTRA_WORD, thoseItems.get(i));
        extras.putInt(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        intent.putExtras(extras);
        row.setOnClickFillInIntent(android.R.id.text1, intent);

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        Timber.i("");
        return null;
    }

    @Override
    public int getViewTypeCount() {
        Timber.i("");
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
