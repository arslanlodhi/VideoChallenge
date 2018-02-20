package challenge.forza.videochallenge.base;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


import javax.inject.Inject;

import challenge.forza.videochallenge.enums.ViewModelEventsEnum;
import challenge.forza.videochallenge.interfaces.ViewModelCallBackObserver;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * @author Nauman Ashraf.
 */

public abstract class BaseActivity<VM extends BaseViewModel,DB extends ViewDataBinding> extends AppCompatActivity implements ViewModelCallBackObserver, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    @Inject
    ViewModelProvider.Factory viewModelFactory;


    public DB binding;
    public VM viewModel;

    @Override
    public abstract void onObserve(ViewModelEventsEnum event, Object eventMessage);

    public abstract Class<VM> getViewModel();

    @LayoutRes
    public abstract int getLayoutRes();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getLayoutRes() );
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(getViewModel());
        viewModel.addObserver(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;



    }
}
