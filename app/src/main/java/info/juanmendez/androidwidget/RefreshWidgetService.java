package info.juanmendez.androidwidget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


/**
 * Created by Juan Mendez on 5/15/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class RefreshWidgetService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}