package com.digivalet.data.remote;

import com.digivalet.data.dto.ArticleResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DataManager {
    private static DataManager dataManager;
    private Api apiService;

    private DataManager(String base_url) {
        apiService = ApiFactory.getClient(base_url).create(Api.class);
    }

    public static DataManager getInstance(String base_url) {
        synchronized (DataManager.class) {
            if (dataManager == null) {
                dataManager = new DataManager(base_url);
            }
            return dataManager;
        }
    }

    public void getTopStories(ResponseObserver<List<Integer>> responseObserver) {
        new CompositeDisposable().add(
                apiService
                        .getTopStories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Integer>>() {
                            @Override
                            public void onSuccess(@NonNull List<Integer> list) {
                                responseObserver.onResponseSuccess(list);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                responseObserver.onResponseFailure(e.getMessage());
                            }
                        }));
    }

    public void getArticle(int id, ResponseObserver<ArticleResponse> responseObserver) {
        new CompositeDisposable().add(
                apiService
                        .getArticle(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ArticleResponse>() {
                            @Override
                            public void onSuccess(@NonNull ArticleResponse response) {
                                responseObserver.onResponseSuccess(response);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                responseObserver.onResponseFailure(e.getMessage());
                            }
                        }));
    }
}
