package challenge.forza.videochallenge.interfaces;


import challenge.forza.videochallenge.enums.ViewModelEventsEnum;

public interface ViewModelCallBackObserver<T> {

    void onObserve(ViewModelEventsEnum event, T eventMessage);

}
