package info.juanmendez.androidwidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText desiredValue;
    static final String RANDOM_INT = "mainActivity-randomInt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.submit);
        desiredValue = (EditText) findViewById(R.id.desiredValue);

        ComponentName componentName = new ComponentName( this, OurWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        submit.setOnClickListener(view -> {
            int[] widgetIds = appWidgetManager.getAppWidgetIds(componentName);
            int value = Integer.parseInt(desiredValue.getText().toString());

            /**
             * Widget's onReceive is only caring about a single widget.
             * So that's why we iterate, but otherwise we could have used
             * EXTRA_APPWIDGET_IDS
             */
            for( int widgetId: widgetIds ){
                Timber.i( "update " + widgetId );

                Intent intent = new Intent(MainActivity.this, OurWidgetProvider.class );
                intent.putExtra(RANDOM_INT, value);
                intent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId );
                sendBroadcast( intent );
            }
        });
    }
}
