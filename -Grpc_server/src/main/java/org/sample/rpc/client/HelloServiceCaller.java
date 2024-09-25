package org.sample.rpc.client;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.sample.proto.HelloRequest;
import org.sample.proto.HelloResponse;
import org.sample.proto.HelloServiceGrpc;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelloServiceCaller {

    private ManagedChannel managedChannel;
    private HelloServiceGrpc.HelloServiceBlockingStub blockingStub;
    private HelloServiceGrpc.HelloServiceStub asynStub;
    private HelloServiceGrpc.HelloServiceFutureStub futureStub;

    public HelloServiceCaller(String domain, int port) {
        managedChannel = ManagedChannelBuilder.forAddress(domain, port)
                .usePlaintext()
                .build();
        blockingStub = HelloServiceGrpc.newBlockingStub(managedChannel);
        asynStub = HelloServiceGrpc.newStub(managedChannel);
        futureStub = HelloServiceGrpc.newFutureStub(managedChannel);
    }

    public void sendBlockingUnary(HelloRequest request) {
        System.out.println("[step1] client 1 server 1 blocking");
        HelloResponse helloResponse = blockingStub.hello(request);
        System.out.println("[step1 result] " + helloResponse.getGreeting());
        System.out.println("[step1 end]");
    }

    public void sendAsynUnary(HelloRequest request) {
        System.out.println("[step2] client1 server 1 asyn");
        asynStub.hello(request, new StreamObserver<>() {
            @Override
            public void onNext(HelloResponse helloResponse) {
                System.out.println("[step2 result] " + helloResponse.getGreeting());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onCompleted() {
                System.out.println("[step2 method end]");
            }
        });
        System.out.println("[step2 end]");
    }

    public void sendFutureUnary(HelloRequest request) {
        System.out.println("[step3] client 1 server 1 future");
        ListenableFuture<HelloResponse> future = futureStub.hello(request);
        HelloResponse response = null;
        try {
            response = future.get(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[step3 result] " + response.getGreeting());
        System.out.println("[step3 end]");
    }

    public void sendBlockingServerStream(HelloRequest request) {
        System.out.println("[step4] client 1 server n blocking");
        Iterator<HelloResponse> responseIter = blockingStub.helloServerStream(request);
        responseIter.forEachRemaining(response -> {
            System.out.println("[step4 result] " + response.getGreeting());
        });
        System.out.println("[step4 end]");
    }

    public void sendAsynServerStream(HelloRequest request) {
        System.out.println("[step5] client 1 server n asyn");
        asynStub.helloServerStream(request, new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse helloResponse) {
                System.out.println("[step5 result] " + helloResponse.getGreeting());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onCompleted() {
                System.out.println("[step5 method end]");
            }
        });
        System.out.println("[step5 end]");
    }


    public void sendAsynClientStream(List<HelloRequest> requestList) {
        System.out.println("[step6] client n server 1 asyn");
        StreamObserver<HelloResponse> responseObserver = new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse helloResponse) {
                System.out.println("[step6 result] " + helloResponse.getGreeting());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onCompleted() {
                System.out.println("[step6 method end]");
            }
        };
        StreamObserver<HelloRequest> requestObserver = asynStub.helloClientStream(responseObserver);
        for (HelloRequest request : requestList) {
            requestObserver.onNext(request);
        }
        requestObserver.onCompleted();
        System.out.println("[step6 end]");
    }

    public void sendAsynBiStream(List<HelloRequest> requestList) {
        System.out.println("[step7] client n server n asny");
        StreamObserver<HelloResponse> responseObsever = new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse helloResponse) {
                System.out.println("[step7 result] " + helloResponse.getGreeting());
            }


            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onCompleted() {
                System.out.println("[step7 method end]");
            }
        };

        StreamObserver<HelloRequest> requestObsever = asynStub.helloBiStream(responseObsever);

        for (HelloRequest request : requestList) {
            requestObsever.onNext(request);
        }

        requestObsever.onCompleted();
        System.out.println("[step7 end]");
    }
}