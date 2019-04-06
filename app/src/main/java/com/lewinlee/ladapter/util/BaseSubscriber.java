package com.lewinlee.ladapter.util;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author: ly
 * @date : 2017/12/4
 * @desc :
 */
public abstract class BaseSubscriber<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onComplete() {

    }
}
