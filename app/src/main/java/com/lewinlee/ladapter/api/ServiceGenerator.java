package com.lewinlee.ladapter.api;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * @author: ly
 * @date : 2017/12/4
 * @desc :
 */
public class ServiceGenerator {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.baidu.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(initOkHttpClient())
            .build();

    private static HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Timber.d("Retrofit: %s", message);
                }
            });

    private static OkHttpClient initOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool())
                .build();
    }

    public static <T> T getService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
