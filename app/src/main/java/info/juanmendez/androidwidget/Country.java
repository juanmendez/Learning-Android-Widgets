package info.juanmendez.androidwidget;

import io.realm.annotations.PrimaryKey;

/**
 * Created by Juan Mendez on 5/11/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class Country {

    @PrimaryKey
    private int id;

    private String country;

    public Country(int id, String country) {
        this.id = id;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }
}
