package com.killrvideo.grpc;

import org.springframework.util.Assert;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import killrvideo.comments.CommentsServiceGrpc;
import killrvideo.comments.CommentsServiceGrpc.CommentsServiceBlockingStub;

/**
 * A Unit Test or consumer may want to USE the running gRPC API.
 *
 * @author DataStax Evangelist Team
 */
public class KillrVideoGrpcClient {
    
    /** gRPC Endpoint */
    private ManagedChannel grpcEndPoint;
   
    /** Clients for different services in gRPC. */
    public CommentsServiceBlockingStub commentServiceGrpcClient;
    
    /**
     * Connection to gRPC Server.
     * 
     * @param grpcServer
     *      current gRPC hostname
     * @param grpcPort
     *      current gRPC portnumber
     */
    public KillrVideoGrpcClient(String grpcServer, int grpcPort) {
       this(ManagedChannelBuilder.forAddress(grpcServer, grpcPort).usePlaintext(true).build());
    }
    
    /**
     * Extension point for your own gRPC channel.
     * 
     * @param grpcEndpoint
     *      current gRPC Channel
     */
    public KillrVideoGrpcClient(ManagedChannel grpcEndpoint) {
        this.grpcEndPoint = grpcEndpoint;
        initServiceClients();
    }
    
    /**
     * Init item
     */
    public void initServiceClients() {
        Assert.notNull(grpcEndPoint, "grpcEndpoint must be setup");
        commentServiceGrpcClient = CommentsServiceGrpc.newBlockingStub(grpcEndPoint);
    }

    /**
     * Getter accessor for attribute 'commentServiceGrpcClient'.
     *
     * @return
     *       current value of 'commentServiceGrpcClient'
     */
    public CommentsServiceBlockingStub getCommentService() {
        return commentServiceGrpcClient;
    }
   
}
