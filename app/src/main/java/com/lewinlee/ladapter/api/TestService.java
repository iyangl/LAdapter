package com.lewinlee.ladapter.api;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * @author: ly
 * @date : 2017/12/7
 * @desc :
 */
public interface TestService {

    @GET("www.baidu.com/api/v1/live")
    Flowable<List<String>> live();
}
