package info.juanmendez.androidwidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import info.juanmendez.androidwidget.dependencies.RealmProvider;
import info.juanmendez.androidwidget.models.Country;
import info.juanmendez.androidwidget.utils.RealmUtils;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Activity is able to update the list which is used by the widgets
 * @see //goo.gl/vZ3wzs
 */
public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText desiredValue;

    @Inject
    RealmList<Country> countries;

    @Inject
    RealmProvider realmProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WidgetApp.getAppComponent().inject( this );

        submit = (Button) findViewById(R.id.submit);
        desiredValue = (EditText) findViewById(R.id.desiredValue);

        ComponentName componentName = new ComponentName( this, OurWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        submit.setOnClickListener(view -> {

            int[] widgetIds = appWidgetManager.getAppWidgetIds(componentName);

            int nextId = MainActivity.this.realmProvider.getNextPrimaryKey(Country.class, "id" );
            Realm realm = MainActivity.this.realmProvider.getRealm();

            realm.executeTransactionAsync(thisRealm -> {
                thisRealm.copyToRealm( new Country(nextId, desiredValue.getText().toString()) );
            }, () -> {
                RealmResults<Country> countries = realm.where(Country.class).findAll();
                RealmUtils.cloneToRealmList( countries, this.countries);
                appWidgetManager.notifyAppWidgetViewDataChanged( widgetIds, R.id.listView );
            }, error -> {
                Timber.e( error.getMessage() );
            });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        String word=getIntent().getStringExtra(OurWidgetProvider.COUNTRY_PICKED);

        if (word != null) {
            Toast.makeText(this, "received: " + word, Toast.LENGTH_LONG ).show();
        }
    }
}
