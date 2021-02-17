package com.digivalet.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Rupesh Saxena
 */

class ApiFactory {
    private static Retrofit retrofit = null;
    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Retrofit getClient(String url) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = httpClient.addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES).
                readTimeout(5, TimeUnit.MINUTES).
                writeTimeout(5, TimeUnit.MINUTES)
                .build();
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
