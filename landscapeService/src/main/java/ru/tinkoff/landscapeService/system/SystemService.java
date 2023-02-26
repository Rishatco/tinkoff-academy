package ru.tinkoff.landscapeService.system;

import com.google.protobuf.Empty;
import io.grpc.Channel;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelFactory;
import net.devh.boot.grpc.client.config.GrpcChannelsProperties;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.StatusServiceGrpc.StatusServiceBlockingStub;
import ru.tinkoff.proto.VersionResponse;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final GrpcChannelsProperties grpcChannelsProperties;
    private final GrpcChannelFactory grpcChannelFactory;
    private final String[] servicesNameMask = {"rancherService", "handymanService"};

    public Map<String, List<Status>> getStatus(){
        Map<String, List<Status>> connectedServicesStatus = initServicesInfoMap();
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
            connectedServicesStatus.get(getServiceMaskFromName(channelName)).add(status);
        }


        return  connectedServicesStatus;
    }

    private Map<String, List<Status>> initServicesInfoMap() {
        Map<String, List<Status>> connectedServicesStatus = new HashMap<>();
        for (String serviceNameMask:  servicesNameMask)
            connectedServicesStatus.put(serviceNameMask, new ArrayList<>());
        return  connectedServicesStatus;
    }

    private String getServiceMaskFromName(String channelName){
        return Arrays.stream(servicesNameMask).filter(channelName::contains).findFirst().orElse("UnknownService");
    }
}
