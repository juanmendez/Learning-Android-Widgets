package info.juanmendez.androidwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String word=getIntent().getStringExtra(OurWidgetProvider.EXTRA_WORD);

        if (word != null) {
            Toast.makeText(this, "received: " + word, Toast.LENGTH_LONG ).show();
        }
    }
}
