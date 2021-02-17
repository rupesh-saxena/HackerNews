package com.digivalet.data.remote;

import com.digivalet.data.dto.ArticleResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Rupesh Saxena
 */

interface Api {
    @GET("v0/topstories.json?print=pretty")
    Single<List<Integer>> getTopStories();

    @GET("v0/item/{articleid}.json?print=pretty")
    Single<ArticleResponse> getArticle(@Path("articleid") int id);
}
