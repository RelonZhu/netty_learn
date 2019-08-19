package com.relon.grpc;

import com.relon.grpcproto.MyRequest;
import com.relon.grpcproto.MyResponse;
import com.relon.grpcproto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub =
                StudentServiceGrpc.newBlockingStub(managedChannel);
        MyResponse response =
                blockingStub.getRealNameByUserName(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(response.getRealname());


    }
}
