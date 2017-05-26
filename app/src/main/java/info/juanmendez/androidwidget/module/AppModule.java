package info.juanmendez.androidwidget.module;

import dagger.Module;
import dagger.Provides;
import info.juanmendez.androidwidget.WidgetApp;

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
}
