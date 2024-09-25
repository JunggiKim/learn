package org.sample.rpc.sever;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.sample.service.HelloServiceImpl;

import java.io.IOException;

public class HelloServiceGrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new HelloServiceImpl())
                .build();

        server.start();
        server.awaitTermination();
    }
}