package com.alihmzyv.squarerootservice;

import com.alihmzyv.squarerootservice.service.SquareRootServiceImpl;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Server implements CommandLineRunner {
    @Value("${grpc.server.port}")
    private Integer port;

    @Override
    public void run(String... args) throws Exception {
        io.grpc.Server server = ServerBuilder.forPort(port)
                .addService(new SquareRootServiceImpl())
                .build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received shutdown request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();
    }
}
