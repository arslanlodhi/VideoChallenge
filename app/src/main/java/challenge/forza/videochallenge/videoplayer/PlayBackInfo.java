package challenge.forza.videochallenge.videoplayer;

/**
 * Created by arslanlodhi on 2/20/18.
 */

public class PlayBackInfo {


    long currentPosition;

    public PlayBackInfo( int currentWindow,long currentPosition) {
        this.currentPosition = currentPosition;
        this.currentWindow = currentWindow;
    }

    int currentWindow;




    public long getCurrentPosition() {
        return currentPosition;
    }

    public int getCurrentWindow() {
        return currentWindow;
    }
}
