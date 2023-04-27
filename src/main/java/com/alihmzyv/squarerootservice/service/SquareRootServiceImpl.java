package com.alihmzyv.squarerootservice.service;

import com.proto.squareroot.SquareRootRequest;
import com.proto.squareroot.SquareRootResponse;
import com.proto.squareroot.SquareRootServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class SquareRootServiceImpl extends SquareRootServiceGrpc.SquareRootServiceImplBase {
    @Override
    public void squareRoot(SquareRootRequest request, StreamObserver<SquareRootResponse> responseObserver) {
        if (!request.hasInputValue()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Input value should be set")
                    .asRuntimeException());
        } else {
            double inputValue = request.getInputValue();
            if (inputValue < 0) {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("Input value should be equal to or greater than 0")
                        .asRuntimeException());
            } else {
                SquareRootResponse squareRootResponse = SquareRootResponse.newBuilder()
                        .setResult(Math.sqrt(inputValue))
                        .build();
                responseObserver.onNext(squareRootResponse);
                responseObserver.onCompleted();
            }
        }
    }
}
