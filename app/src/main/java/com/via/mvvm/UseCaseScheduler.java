/*
 * 1312424234234
 */
package com.via.mvvm;

/**
 * Created by ShawLiao on 2018/5/9.
 */

import android.support.annotation.Nullable;

/**
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 */
public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                          final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(
            final UseCase.UseCaseCallback<V> useCaseCallback, int errCode , @Nullable String desc);
}

