package challenge.forza.videochallenge.videoplayer;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import challenge.forza.videochallenge.BR;

/**
 * Created by arslanlodhi on 2/18/18.
 */

public class PlayBackConfiguration extends BaseObservable {

    boolean isMute;

    @Bindable
    public boolean isMute() {
        return isMute;
    }

    public void setMute(boolean mute) {
        isMute = mute;
        this.notifyPropertyChanged(BR.mute);
    }
}
