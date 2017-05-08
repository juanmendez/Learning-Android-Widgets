package info.juanmendez.androidwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //we want to skip this for now.
        //setContentView(R.layout.activity_main);
        finish();
    }
}
