package info.juanmendez.androidwidget.dependencies;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Juan Mendez on 5/19/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
@Singleton
public class RealmModels {

    private RealmProvider realmProvider;

    @Inject
    public RealmModels(RealmProvider realmProvider ) {

    }
}
