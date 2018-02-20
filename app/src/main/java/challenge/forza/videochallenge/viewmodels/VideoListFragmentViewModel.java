package challenge.forza.videochallenge.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.view.View;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import java.util.ArrayList;
import java.util.HashMap;
import javax.inject.Inject;
import challenge.forza.videochallenge.R;
import challenge.forza.videochallenge.base.BaseViewModel;
import challenge.forza.videochallenge.videoplayer.ExoPlayerHolder;
import challenge.forza.videochallenge.models.VideoModel;
import challenge.forza.videochallenge.repositories.VideoRepository;
import challenge.forza.videochallenge.videoplayer.PlayBackConfiguration;
import challenge.forza.videochallenge.videoplayer.PlayBackInfo;
import challenge.forza.videochallenge.videoplayer.VideoPlayerAdapter;


public class VideoListFragmentViewModel extends BaseViewModel {//implements SurfaceHolder.Callback{

    private MutableLiveData<ArrayList<VideoModel>> videoList = new MutableLiveData<>();
    private VideoPlayerAdapter mVideoPlayerAdapter;
    HashMap<Integer, PlayBackInfo> playerList = new HashMap<>();
    MutableLiveData<PlayBackConfiguration> playBackInfo=new MutableLiveData<>();
    int lastLoadedPosition = 0;
    SimpleExoPlayerView lastExoPlayer;


    @Inject
    VideoRepository repository;

    @Inject
    public VideoListFragmentViewModel() {
        playBackInfo=new MutableLiveData<>();

    }

    public void intialize(Context context) {
        mVideoPlayerAdapter = new ExoPlayerHolder(context);

    }

    public MutableLiveData<PlayBackConfiguration> getPlayBackConfiguration() {
        playBackInfo.postValue(new PlayBackConfiguration());
        return playBackInfo;

    }

    public MutableLiveData<ArrayList<VideoModel>> getVideoList() {
        videoList.postValue(repository.getVideoList());
        return videoList;

    }

    public void play(View view, int position) {
        playerList.put(lastLoadedPosition, mVideoPlayerAdapter.stop());
        lastLoadedPosition = position;

        SimpleExoPlayerView exoPlayerView = view.findViewById(R.id.player_view);

        if (lastExoPlayer != null)
            lastExoPlayer.setPlayer(null);

        lastExoPlayer = exoPlayerView;
        mVideoPlayerAdapter.loadMedia(videoList.getValue().get(position).getUrl(), exoPlayerView, playerList.get(position));

    }

    public void toggleMute() {
        if(playBackInfo.getValue().isMute())
            playBackInfo.getValue().setMute(mVideoPlayerAdapter.setMute(false));
        else
            playBackInfo.getValue().setMute(mVideoPlayerAdapter.setMute(true));
    }

    public void release() {
        mVideoPlayerAdapter.release();
    }

}
