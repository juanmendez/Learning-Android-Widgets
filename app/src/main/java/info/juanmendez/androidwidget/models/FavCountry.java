package info.juanmendez.androidwidget.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Juan Mendez on 5/13/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class FavCountry extends RealmObject implements RealmClonable<FavCountry> {
    @PrimaryKey
    private int favId;

    private String name;

    public FavCountry(String country) {
        this.favId = 1;
        this.name = country;
    }

    public FavCountry() {
        super();
    }

    public int getFavId() {
        return favId;
    }

    public String getName() {
        return name;
    }

    @Override
    public FavCountry realmClone() {
        return new FavCountry(name);
    }

}
