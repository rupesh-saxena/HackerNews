package com.digivalet.data.remote;

public interface ResponseObserver<T> {

    void onResponseSuccess(T object);

    void onResponseFailure(String message);

    default void onInternetFailure(){};
}
