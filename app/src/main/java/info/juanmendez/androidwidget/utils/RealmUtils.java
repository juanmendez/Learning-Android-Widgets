package info.juanmendez.androidwidget.utils;

import info.juanmendez.androidwidget.models.RealmClonable;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Juan Mendez on 5/11/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
public class RealmUtils {

    /**
     * Update a realmList from a realmResults.
     * The purpose is to enable realm data to make it into widgets which run
     * in a different thread.
     *
     * @param realmResults
     * @param realmList it gets cleared and populated with realmClonable objects
     * @param <T> T represent any class which implements RealmClonable
     */
    public static <T extends RealmClonable<T>> void cloneToRealmList(RealmResults<T> realmResults, RealmList<T> realmList ){
        realmList.clear();

        for( T realmModel: realmResults ){
            realmList.add(realmModel.realmClone());
        }
    }
}