package challenge.forza.videochallenge.videoplayer;

import android.content.Context;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;


public interface VideoPlayerAdapter {


    void loadMedia(String mediaUrl, SimpleExoPlayerView simpleExoPlayerView, PlayBackInfo playBackInfo);

    void release();

    boolean isPlaying();

    boolean setMute(boolean mute);

    PlayBackInfo stop();

}