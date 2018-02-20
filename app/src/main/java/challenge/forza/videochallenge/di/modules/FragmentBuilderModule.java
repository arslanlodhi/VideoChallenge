package challenge.forza.videochallenge.di.modules;


import challenge.forza.videochallenge.views.VideoListFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract VideoListFragment contributeVideoListFragment();


}