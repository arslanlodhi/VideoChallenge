package challenge.forza.videochallenge.views;

/**
 * Created by arslanlodhi on 2/13/18.
 */

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import challenge.forza.videochallenge.R;
import challenge.forza.videochallenge.adapters.VideoListAdapter;
import challenge.forza.videochallenge.base.BaseFragment;
import challenge.forza.videochallenge.databinding.FragmentVideoBinding;
import challenge.forza.videochallenge.enums.ViewModelEventsEnum;
import challenge.forza.videochallenge.models.VideoModel;
import challenge.forza.videochallenge.ui.CustomRecyclerView;
import challenge.forza.videochallenge.videoplayer.PlayBackConfiguration;
import challenge.forza.videochallenge.viewmodels.VideoListFragmentViewModel;


public class VideoListFragment extends BaseFragment<VideoListFragmentViewModel, FragmentVideoBinding> {

    private static final String TAG = VideoListFragment.class.getSimpleName();
    public final static String LIST_STATE_KEY = "recycler_list_state";

    CustomRecyclerView recyclerView;
    VideoListAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
    Parcelable listState;
    Handler handler = new Handler(Looper.getMainLooper());


    public VideoListFragment() { /**/ }


    @Override
    public Class<VideoListFragmentViewModel> getViewModel() {
        return VideoListFragmentViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_video;
    }

    @Override
    public void onObserve(ViewModelEventsEnum event, Object eventMessage) {
        Log.e("Here" + eventMessage, "" + event);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = rootView.findViewById(R.id.rc_view);
        setRetainInstance(true);
        viewModel.intialize(getContext());
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new VideoListAdapter(getContext(), viewModel, new VideoListAdapter.OnMuteButtonPressed() {
            @Override
            public void onPressed() {
                viewModel.toggleMute();
            }
        });
        recyclerView.setAdapter(mAdapter);
        viewModel.getVideoList().observe(this, new Observer<ArrayList<VideoModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<VideoModel> videoModels) {
                mAdapter.setItems(videoModels);
            }
        });
        viewModel.getPlayBackConfiguration().observe(this, new Observer<PlayBackConfiguration>() {
            @Override
            public void onChanged(@Nullable PlayBackConfiguration playBackConfiguration) {
                mAdapter.setPlayBackConfiguration(playBackConfiguration);
            }
        });
        recyclerView.setOnVisibileItemChange(new CustomRecyclerView.OnVisibileItemChange() {
            @Override
            public void onVisibileItemChange(int position,View view) {
                viewModel.play(view,position);
            }
        });



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        listState = layoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.release();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.release();
    }

}