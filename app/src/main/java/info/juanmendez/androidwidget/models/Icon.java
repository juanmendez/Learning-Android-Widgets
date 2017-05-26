package info.juanmendez.androidwidget.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Juan Mendez on 5/13/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */
public class Icon extends RealmObject implements RealmClonable<Icon> {
    @PrimaryKey
    private int id;

    private int iconId;

    public Icon(int iconId) {
        this.id = 1;
        this.iconId = iconId;
    }

    public Icon() {
        super();
    }

    public int getIconId() {
        return iconId;
    }

    @Override
    public Icon realmClone() {
        return new Icon(iconId);
    }

}
