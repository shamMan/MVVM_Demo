/*
 * 1312424234234
 */
package com.via.demo.base.NavBar;

import android.support.annotation.Nullable;

import com.via.demo.base.BaseRepository;

/**
 * Created by ShawLiao on 2018/5/13.
 */
public interface LocationRepository extends BaseRepository {

    void start(final LocationRepository.RepositoryCallBack callBack);
    void stop();

    interface RepositoryCallBack {
        void onError(int errorCode , @Nullable String desc);
    }
}
