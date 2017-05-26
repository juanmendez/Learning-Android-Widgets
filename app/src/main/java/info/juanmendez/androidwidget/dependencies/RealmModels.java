package info.juanmendez.androidwidget.dependencies;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.juanmendez.androidwidget.models.Icon;
import io.realm.Realm;

/**
 * Created by Juan Mendez on 5/19/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
@Singleton
public class RealmModels {

    private RealmProvider realmProvider;
    private Realm realm;

    @Inject
    public RealmModels(RealmProvider realmProvider ) {
        this.realmProvider = realmProvider;
        this.realm = realmProvider.getRealm();
    }

    public Icon getIcon(){
        Icon icon = realm.where( Icon.class ).findFirst();
        return icon;
    }
}
