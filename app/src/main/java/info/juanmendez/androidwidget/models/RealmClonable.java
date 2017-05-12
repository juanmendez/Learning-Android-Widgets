package info.juanmendez.androidwidget.models;

import io.realm.RealmModel;


/**
 * Created by Juan Mendez on 5/11/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public interface RealmClonable<T> extends RealmModel {
    T realmClone();
}
