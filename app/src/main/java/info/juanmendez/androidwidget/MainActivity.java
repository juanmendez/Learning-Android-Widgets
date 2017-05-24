package info.juanmendez.androidwidget;

import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

import java.util.Random;

import javax.inject.Inject;

import info.juanmendez.androidwidget.dependencies.RealmModels;
import info.juanmendez.androidwidget.dependencies.RealmProvider;
import timber.log.Timber;

/**
 * Activity is able to update the list which is used by the widgets
 * @see //goo.gl/vZ3wzs
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    RealmProvider realmProvider;

    @Inject
    RealmModels realmModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WidgetApp.getAppComponent().inject( this );

        Timber.i( "color" + (new Random()).nextInt(4) );
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
