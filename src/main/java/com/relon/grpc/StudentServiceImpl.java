package com.relon.grpc;

import com.relon.grpcproto.MyRequest;
import com.relon.grpcproto.MyResponse;
import com.relon.grpcproto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端的信息" + request.getUsername());

        MyResponse myResponse = MyResponse.newBuilder().setRealname("张三").build();

        responseObserver.onNext(myResponse);
        responseObserver.onCompleted();
    }
}
