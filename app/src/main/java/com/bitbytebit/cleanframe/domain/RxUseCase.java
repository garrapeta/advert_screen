package com.bitbytebit.cleanframe.domain;

import rx.Observable;

/**
 * {@link UseCase} returning an {@link Observable} as the ResponseModel
 *
 * @param <RequestModel>
 * @param <ResponseModel>
 */
public interface RxUseCase<RequestModel, ResponseModel> {

    Observable<ResponseModel> build(RequestModel requestValues);
}
