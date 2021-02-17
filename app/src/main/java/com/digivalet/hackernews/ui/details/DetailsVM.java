package com.digivalet.hackernews.ui.details;

import com.digivalet.hackernews.base.BaseViewModel;

public class DetailsVM extends BaseViewModel<DetailsNavigator> {
    public void init() {
        getNavigator().init();
    }

    public void setWebView(String url) {
        getNavigator().setWebView(url);
    }
}
