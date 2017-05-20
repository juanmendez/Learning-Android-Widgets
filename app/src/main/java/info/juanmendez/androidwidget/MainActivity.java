package info.juanmendez.androidwidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import info.juanmendez.androidwidget.dependencies.RealmModels;
import info.juanmendez.androidwidget.dependencies.RealmProvider;
import info.juanmendez.androidwidget.models.Country;
import info.juanmendez.androidwidget.models.FavCountry;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Activity is able to update the list which is used by the widgets
 * @see //goo.gl/vZ3wzs
 */
public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText countryText;
    EditText favCountryText;

    @Inject
    RealmProvider realmProvider;

    @Inject
    RealmModels realmModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WidgetApp.getAppComponent().inject( this );

        submit = (Button) findViewById(R.id.submit);
        countryText = (EditText) findViewById(R.id.countryText);
        favCountryText = (EditText) findViewById(R.id.favCountryText);

        ComponentName componentName = new ComponentName( this, OurWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        FavCountry  favCountry = realmProvider.getRealm().where( FavCountry.class ).findFirst();

        submit.setOnClickListener(view -> {

            int[] widgetIds = appWidgetManager.getAppWidgetIds(componentName);

            int nextId = MainActivity.this.realmProvider.getNextPrimaryKey(Country.class, "id" );
            Realm realm = MainActivity.this.realmProvider.getRealm();

            if( !countryText.getText().toString().isEmpty() ){
                realm.executeTransactionAsync(thisRealm -> {
                    thisRealm.copyToRealm( new Country(nextId, countryText.getText().toString()) );
                }, () -> {
                    //we want to update the list once so all widgets use it.
                    realmModels.getCountries();

                    appWidgetManager.notifyAppWidgetViewDataChanged( widgetIds, R.id.listView );
                }, error -> {
                    Timber.e( error.getMessage() );
                });
            }

            if( !favCountryText.getText().toString().isEmpty() ){
                realm.executeTransactionAsync(thisRealm -> {
                    thisRealm.copyToRealmOrUpdate( new FavCountry(favCountryText.getText().toString()) );
                }, () -> {
                    Intent intent = new Intent(MainActivity.this, OurWidgetProvider.class );
                    intent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_IDS, widgetIds );
                    sendBroadcast( intent );
                }, error -> {
                    Timber.e( error.getMessage() );
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String word=getIntent().getStringExtra(OurWidgetProvider.COUNTRY_PICKED);
        int widgetid = getIntent().getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,0);

        if (word != null) {
            Toast.makeText(this, "received " + widgetid + " country-name:" + word, Toast.LENGTH_LONG ).show();
        }
    }
}
