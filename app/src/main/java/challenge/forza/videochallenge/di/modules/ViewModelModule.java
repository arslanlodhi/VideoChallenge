package challenge.forza.videochallenge.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import challenge.forza.videochallenge.di.ViewModelFactory;
import challenge.forza.videochallenge.viewmodels.VideoListFragmentViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(VideoListFragmentViewModel.class)
    abstract ViewModel videoListFragmentViewModel(VideoListFragmentViewModel videoListFragmentViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);


}