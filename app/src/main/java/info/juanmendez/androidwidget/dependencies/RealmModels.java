package info.juanmendez.androidwidget.dependencies;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.juanmendez.androidwidget.models.Country;
import info.juanmendez.androidwidget.models.FavCountry;
import info.juanmendez.androidwidget.utils.RealmUtils;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Juan Mendez on 5/19/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
@Singleton
public class RealmModels {

    private RealmProvider realmProvider;
    RealmList<Country> countryList = new RealmList<>();
    private Realm realm;

    @Inject
    public RealmModels(RealmProvider realmProvider ) {
        this.realmProvider = realmProvider;
        this.realm = realmProvider.getRealm();
    }

    public FavCountry getFavoriteCountry(){
        FavCountry country = realm.where( FavCountry.class ).findFirst();

        return country;
    }

    public RealmList<Country> getCountries(){

        RealmResults<Country> countries  = realm.where(Country.class).findAll();

        if( countries != null ){
            RealmUtils.cloneToRealmList( countries, countryList);
        }

        return countryList;
    }
}
