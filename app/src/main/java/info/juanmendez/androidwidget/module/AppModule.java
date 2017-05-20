package info.juanmendez.androidwidget.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.juanmendez.androidwidget.WidgetApp;
import info.juanmendez.androidwidget.dependencies.RealmProvider;
import info.juanmendez.androidwidget.models.Country;
import info.juanmendez.androidwidget.models.FavCountry;
import info.juanmendez.androidwidget.utils.RealmUtils;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Juan Mendez on 5/10/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

@Module
public class AppModule {

    WidgetApp app;

    public AppModule( WidgetApp app ){
        this.app = app;
    }

    @Provides
    public WidgetApp providesApp(){
        return this.app;
    }

    @Provides
    @Singleton
    public RealmList<Country> providesCountries(RealmProvider realmProvider){
        RealmList<Country> list = new RealmList<>();

        if( realmProvider.getRealm() != null ){

            RealmResults<Country> countries  = realmProvider.getRealm().where(Country.class).findAll();

            if( countries != null ){
                RealmUtils.cloneToRealmList( countries, list );
            }

        }

        return list;
    }

    @Provides
    @Singleton
    public FavCountry provideFavoriteCountry(RealmProvider realmProvider){

        FavCountry country = realmProvider.getRealm().where( FavCountry.class ).findFirst();

        //wait a minute, the table can be empty
        if( country == null ){
            country = new FavCountry("");
        }

        return country;
    }
}
