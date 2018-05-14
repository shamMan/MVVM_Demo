/*
 * 1312424234234
 */
package com.via.demo.base.NavBar;

import android.support.annotation.NonNull;

import com.via.demo.base.UseCase;

/**
 * Created by ShawLiao on 2018/5/13.
 */
public class UseCaseStopLocation extends UseCase<UseCaseStopLocation.RequestValues , UseCaseStopLocation.ResponseValue> {

    private LocationRepository mRepository;

    public UseCaseStopLocation(@NonNull LocationRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void executeUseCase(final UseCaseStopLocation.RequestValues values) {
        mRepository.stop();
    }

    public static final class RequestValues implements UseCase.RequestValues {
        public RequestValues() { }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        public ResponseValue() {}
    }
}
