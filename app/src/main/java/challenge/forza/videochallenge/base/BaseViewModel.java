package challenge.forza.videochallenge.base;

import android.arch.lifecycle.ViewModel;

import challenge.forza.videochallenge.enums.ViewModelEventsEnum;
import challenge.forza.videochallenge.interfaces.ViewModelCallBackObserver;


public class BaseViewModel extends ViewModel {

    public ViewModelCallBackObserver callBackObserver;

    public void addObserver(ViewModelCallBackObserver callBackObserver) {
        this.callBackObserver = callBackObserver;
    }

    public void removeObserver() {
        callBackObserver = null;
    }

    public void notifyObserver(ViewModelEventsEnum eventType, String message){
        if(callBackObserver != null)
        callBackObserver.onObserve(eventType, message);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        removeObserver();
    }
}
