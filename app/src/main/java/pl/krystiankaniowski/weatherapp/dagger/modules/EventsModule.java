package pl.krystiankaniowski.weatherapp.dagger.modules;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import pl.krystiankaniowski.weatherapp.dagger.scope.ApplicationScope;

@Module
public class EventsModule {

    @Provides
    @ApplicationScope
    public EventBus providesEventBus() {
        return EventBus.getDefault();
    }

}
