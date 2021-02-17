package com.digivalet.hackernews.ui.list;

import com.digivalet.data.remote.DataManager;
import com.digivalet.data.remote.ResponseObserver;
import com.digivalet.data.dto.ArticleResponse;
import com.digivalet.data.local.PreferenceManager;
import com.digivalet.hackernews.base.BaseViewModel;
import com.digivalet.hackernews.ui.App;
import com.digivalet.hackernews.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class ListVM extends BaseViewModel<ListNavigator> {
    private final DataManager dataManager = getDataManager();
    private final List<ArticleResponse> list = new ArrayList<>();
    private PreferenceManager preference = new PreferenceManager();

    public void init() {
        getNavigator().init();
        getNavigator().getTopStories();
    }


    public void getTopStories() {
        setIsLoading(true);
        dataManager.getTopStories(new ResponseObserver<List<Integer>>() {
            @Override
            public void onResponseSuccess(List<Integer> object) {
                getArticles(object);
            }

            @Override
            public void onResponseFailure(String message) {
                getNavigator().showError(message);
            }
        });
    }

    private void getArticles(List<Integer> object) {
        for (int i = 0; i <= AppConstants.TOTAL_ITEM_COUNT; i++) {
            dataManager.getArticle(object.get(i), new ResponseObserver<ArticleResponse>() {
                @Override
                public void onResponseSuccess(ArticleResponse object) {
                    setIsLoading(false);
                    list.add(object);
                    preference.saveList(App.context, list);
                    getNavigator().setArticleList(list);
                }

                @Override
                public void onResponseFailure(String message) {
                    getNavigator().showError(message);
                }
            });
        }

    }

    public void requestOfflineData() {
        getNavigator().setArticleList(preference.getList(App.context));
    }
}
