package com.digivalet.hackernews.ui.list;

import com.digivalet.data.dto.ArticleResponse;

import java.util.List;

public interface ListNavigator {
    void init();

    void getTopStories();

    void setArticleList(List<ArticleResponse> list);

    void showError(String message);
}
