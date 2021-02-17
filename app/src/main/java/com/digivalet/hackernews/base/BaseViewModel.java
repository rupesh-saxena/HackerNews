package com.digivalet.hackernews.base;


import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.digivalet.data.remote.DataManager;
import com.digivalet.hackernews.utils.AppConstants;

/**
 * Created by Rupesh Saxena
 */

public abstract class BaseViewModel<N> extends ViewModel {

    private N mNavigator;
    private final DataManager dataManager = DataManager.getInstance(AppConstants.BASE_URL);
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);

    public BaseViewModel() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public N getNavigator() {
        return mNavigator;
    }

    public void setNavigator(N mNavigator) {
        this.mNavigator = mNavigator;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
