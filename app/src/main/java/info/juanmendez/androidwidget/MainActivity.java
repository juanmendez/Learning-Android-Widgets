package info.juanmendez.androidwidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity is able to update the list which is used by the widgets
 * @see //goo.gl/vZ3wzs
 */
public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText desiredValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.submit);
        desiredValue = (EditText) findViewById(R.id.desiredValue);



        ComponentName componentName = new ComponentName( this, OurWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        submit.setOnClickListener(view -> {
            WidgetApp.addItem( desiredValue.getText().toString() );

            int[] widgetIds = appWidgetManager.getAppWidgetIds(componentName);
            appWidgetManager.notifyAppWidgetViewDataChanged( widgetIds, R.id.listView );
        });
    }
}
