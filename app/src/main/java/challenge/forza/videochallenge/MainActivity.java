package challenge.forza.videochallenge;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import challenge.forza.videochallenge.base.BaseActivity;
import challenge.forza.videochallenge.base.BaseViewModel;
import challenge.forza.videochallenge.enums.ViewModelEventsEnum;
import challenge.forza.videochallenge.views.VideoListFragment;

public class MainActivity extends BaseActivity<BaseViewModel,ViewDataBinding> {
    private static final String TAG_CAKE_LIST_FRAGMENT = "CakeListFragment";


    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Class<BaseViewModel> getViewModel() {
        return BaseViewModel.class;
    }

    @Override
    public void onObserve(ViewModelEventsEnum event, Object eventMessage) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new VideoListFragment(),TAG_CAKE_LIST_FRAGMENT)
                    .commit();
        }
    }



}
