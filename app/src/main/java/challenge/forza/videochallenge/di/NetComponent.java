package challenge.forza.videochallenge.di;


import android.app.Application;

import javax.inject.Singleton;

import challenge.forza.videochallenge.AppApplication;
import challenge.forza.videochallenge.di.modules.ActivityBuilderModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules={AppModule.class,AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface NetComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        NetComponent build();
    }

    void inject(AppApplication app);

}