package ru.tinkoff.landscapeService.system;

import com.google.protobuf.Empty;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelFactory;
import net.devh.boot.grpc.client.config.GrpcChannelsProperties;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.StatusServiceGrpc.StatusServiceBlockingStub;
import ru.tinkoff.proto.VersionResponse;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final GrpcChannelsProperties grpcChannelsProperties;
    private final GrpcChannelFactory grpcChannelFactory;

    public Map<String, Status[]> getStatus(){
        Map<String, Status[]> connectedServicesStatus = new HashMap<>();
        for(String channelName: grpcChannelsProperties.getClient().keySet()) {
            Channel serviceChannel = grpcChannelFactory.createChannel(channelName);
            StatusServiceBlockingStub statusServiceBlockingStub = StatusServiceGrpc.newBlockingStub(serviceChannel);
            VersionResponse versionResponse = statusServiceBlockingStub.getVersion(Empty.getDefaultInstance());
            ReadinessResponse readinessResponse = statusServiceBlockingStub.getReadiness(Empty.getDefaultInstance());

            Status status = Status.builder().group(versionResponse.getGroup())
                    .name(versionResponse.getName())
                    .status(readinessResponse.getStatus())
                    .artifact(versionResponse.getArtifact())
                    .version(versionResponse.getVersion())
                    .host(serviceChannel.authority())
                    .build();
            connectedServicesStatus.put(channelName, new Status[]{status});
        }
        return  connectedServicesStatus;
    }

}
