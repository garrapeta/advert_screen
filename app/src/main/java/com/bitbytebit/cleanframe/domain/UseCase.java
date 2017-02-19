package com.bitbytebit.cleanframe.domain;

/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <RequestModel> the request type
 * @param <ResponseModel> the response type
 */
public interface UseCase<RequestModel, ResponseModel> {

    String TAG = UseCase.class.getSimpleName();

    ResponseModel execute(RequestModel requestValues);

}
