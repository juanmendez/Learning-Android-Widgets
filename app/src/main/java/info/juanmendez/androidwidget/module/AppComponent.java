package info.juanmendez.androidwidget.module;

import javax.inject.Singleton;

import dagger.Component;
import info.juanmendez.androidwidget.IconService;
import info.juanmendez.androidwidget.IconWidgetProvider;
import info.juanmendez.androidwidget.MainActivity;

/**
 * Created by Juan Mendez on 5/10/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity );
    void inject(IconWidgetProvider widgetProvider );
    void inject( IconService iconService );
}
