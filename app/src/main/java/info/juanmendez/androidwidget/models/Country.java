package info.juanmendez.androidwidget.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Juan Mendez on 5/11/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class Country extends RealmObject implements RealmClonable<Country>{

    @PrimaryKey
    private int id;

    private String name;

    public Country(int id, String country) {
        this.id = id;
        this.name = country;
    }

    public Country() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public Country realmClone() {
        return null;
    }
}