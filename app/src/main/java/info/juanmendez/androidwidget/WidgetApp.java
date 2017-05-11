package info.juanmendez.androidwidget;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;


/**
 * Created by Juan Mendez on 5/8/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class WidgetApp extends Application {

    private static BehaviorSubject<Integer> subject = BehaviorSubject.create();
    private static ArrayList<String> items=  new ArrayList<>( Arrays.asList(new String[]{ "Canada", "United States", "Mexico" }));

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static ArrayList<String> getItems() {
        return items;
    }

    public static void addItem( String string ){
        items.add( string );
        subject.onNext( items.size() );
    }

    public static BehaviorSubject<Integer> getSubject() {
        return subject;
    }
}
