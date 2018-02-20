package challenge.forza.videochallenge.videoplayer;

import android.content.Context;
import android.net.Uri;
import android.view.Surface;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

/**
 * Created by arslanlodhi on 2/20/18.
 */

public class ExoPlayerHolder implements VideoRendererEventListener, VideoPlayerAdapter {
    private static final String TAG = "MainActivity";
    private SimpleExoPlayer player;
    Context context;
    PlayBackInfo playBackInfo;

    public ExoPlayerHolder(Context context) {
        this.context = context;
        intialize();

    }

    public void intialize() {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();

        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
    }


    @Override
    public void loadMedia(String mediaUrl, SimpleExoPlayerView simpleExoPlayerView, PlayBackInfo pPlayBackInfo) {
        if (player == null)
            intialize();

        this.playBackInfo = pPlayBackInfo;
        simpleExoPlayerView.setPlayer(player);

        Uri mp4VideoUri = Uri.parse(mediaUrl);

        DefaultBandwidthMeter bandwidthMeterA = new DefaultBandwidthMeter();
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "exoplayer2example"), bandwidthMeterA);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource videoSource = new HlsMediaSource(mp4VideoUri, dataSourceFactory, 1, null, null);
        final LoopingMediaSource loopingSource = new LoopingMediaSource(videoSource);

        player.prepare(loopingSource);

        player.addListener(new ExoPlayer.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {
            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playBackInfo != null) {
                    player.seekTo(playBackInfo.getCurrentWindow(), playBackInfo.getCurrentPosition());
                    playBackInfo = null;
                }


            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                player.stop();
                player.prepare(loopingSource);
                player.setPlayWhenReady(true);
            }

            @Override
            public void onPositionDiscontinuity() {
            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            }
        });

        player.setPlayWhenReady(true); //run file/link when ready to play.
        player.setVideoDebugListener(this); //for listening to resolution change and  outputing the resolution
    }

    @Override
    public PlayBackInfo stop() {
        if (player != null) {
            player.stop();
            return new PlayBackInfo(player.getCurrentWindowIndex(), player.getCurrentPosition());

        }
        return null;
    }

    @Override
    public boolean isPlaying() {
        return player.isPlayingAd();
    }

    @Override
    public void release() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public boolean setMute(boolean mute) {
        if (mute) {
            player.setVolume(0);
            return true;
        } else {
            player.setVolume(1);
            return false;
        }
    }


    @Override
    public void onVideoEnabled(DecoderCounters counters) {

    }

    @Override
    public void onVideoDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {

    }

    @Override
    public void onVideoInputFormatChanged(Format format) {

    }

    @Override
    public void onDroppedFrames(int count, long elapsedMs) {

    }

    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
    }

    @Override
    public void onRenderedFirstFrame(Surface surface) {

    }

    @Override
    public void onVideoDisabled(DecoderCounters counters) {

    }


}
