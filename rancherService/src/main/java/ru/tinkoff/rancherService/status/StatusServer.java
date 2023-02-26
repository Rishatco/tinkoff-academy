package ru.tinkoff.rancherService.status;

import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.stub.StreamObserver;
import ru.tinkoff.proto.VersionResponse;

@GrpcService
@RequiredArgsConstructor
public class StatusServer extends StatusServiceGrpc.StatusServiceImplBase {

    private final BuildProperties buildProperties;

    /**
     * Get service build Version
     *
     * @param request          empty gRPC request
     * @param responseObserver response observer for sending stream message
     */
    @Override
    public void getVersion(Empty request, StreamObserver<VersionResponse> responseObserver) {
        VersionResponse response = VersionResponse.newBuilder().setVersion(buildProperties.getVersion()).setArtifact(buildProperties.getArtifact()).setGroup(buildProperties.getGroup()).setName(buildProperties.getName()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /**
     *  Get service readiness state
     * @param request empty gRPC request
     * @param responseObserver response observer for sending stream message
     */
    @Override
    public void getReadiness(Empty request, StreamObserver<ReadinessResponse> responseObserver) {
        ReadinessResponse response = ReadinessResponse.newBuilder().setStatus("OK").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}