package info.juanmendez.androidwidget.dependencies;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.juanmendez.androidwidget.WidgetApp;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Juan Mendez on 4/1/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
@Singleton
public class RealmProvider {

    WidgetApp app;
    Realm realm;

    @Inject
    public RealmProvider(WidgetApp app){

        Realm.init( app );
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .name("info.juanmendez.widget_db")
                .build();

        //Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealm(){
        return realm;
    }

    public int getNextPrimaryKey( Class clazz, String idField ){

        int next = 1;

        try {
            AtomicInteger primaryKeyValue = new AtomicInteger(realm.where(clazz).max(idField).intValue());
            return primaryKeyValue.incrementAndGet();
        } catch (Exception e) {
            return next;
        }
    }
}