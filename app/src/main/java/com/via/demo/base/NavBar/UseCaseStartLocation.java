/*
 * 1312424234234
 */

/*
 * 1312424234234
 */
package com.via.demo.base.NavBar;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.via.demo.base.UseCase;

/**
 * 获取定位
 * Created by ShawLiao on 2018/5/13.
 */
public class UseCaseStartLocation extends UseCase<UseCaseStartLocation.RequestValues , UseCaseStartLocation.ResponseValue> {

    private LocationRepository mRepository;

    public UseCaseStartLocation(@NonNull LocationRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void executeUseCase(final UseCaseStartLocation.RequestValues values) {
        mRepository.start(new LocationRepository.RepositoryCallBack() {
            @Override
            public void onError(int errorCode, @Nullable String desc) {
                getUseCaseCallback().onError(errorCode,desc);
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        public RequestValues() { }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        public ResponseValue() {}
    }
}
