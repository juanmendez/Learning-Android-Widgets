package info.juanmendez.androidwidget.module;

import javax.inject.Singleton;

import dagger.Component;
import info.juanmendez.androidwidget.IconService;
import info.juanmendez.androidwidget.MainActivity;
import info.juanmendez.androidwidget.OurWidgetProvider;

/**
 * Created by Juan Mendez on 5/10/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity );
    void inject(OurWidgetProvider widgetProvider );
    void inject( IconService iconService );
}
