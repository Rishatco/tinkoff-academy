package ru.tinkoff.landscapeService.system;

import com.google.protobuf.Empty;
import io.grpc.Channel;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelFactory;
import net.devh.boot.grpc.client.config.GrpcChannelsProperties;
import org.springframework.stereotype.Service;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.StatusServiceGrpc.StatusServiceBlockingStub;
import ru.tinkoff.proto.VersionResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final GrpcChannelsProperties grpcChannelsProperties;
    private final GrpcChannelFactory grpcChannelFactory;


    /**
     *
     * @return Map with services status
     */
    public Map<String, List<Status>> getStatus(){
        Map<String, List<Status>> connectedServicesStatus = initServicesInfoMap();
        for(String channelName: grpcChannelsProperties.getClient().keySet()) {
            Channel serviceChannel = grpcChannelFactory.createChannel(channelName);
            StatusServiceBlockingStub statusServiceBlockingStub = StatusServiceGrpc.newBlockingStub(serviceChannel);
            Status serverStatus = getServiceStatus(statusServiceBlockingStub);
            connectedServicesStatus.get(getServiceMaskFromName(channelName)).add(serverStatus);
        }
        return  connectedServicesStatus;
    }

    private Map<String, List<Status>> initServicesInfoMap() {
        Map<String, List<Status>> connectedServicesStatus = new HashMap<>();
        for (String serviceNameMask:  SystemConst.servicesNameMask)
            connectedServicesStatus.put(serviceNameMask, new ArrayList<>());
        return  connectedServicesStatus;
    }

    private String getServiceMaskFromName(String channelName){
        return Arrays.stream(SystemConst.servicesNameMask).filter(channelName::contains).findFirst().orElse("UnknownService");
    }

    private Status getServiceStatus(StatusServiceBlockingStub statusServiceBlockingStub){
        VersionResponse versionResponse = statusServiceBlockingStub.getVersion(Empty.getDefaultInstance());
        ReadinessResponse readinessResponse = statusServiceBlockingStub.getReadiness(Empty.getDefaultInstance());

        Status status = Status.builder().group(versionResponse.getGroup())
                .name(versionResponse.getName())
                .status(readinessResponse.getStatus())
                .artifact(versionResponse.getArtifact())
                .version(versionResponse.getVersion())
                .host(statusServiceBlockingStub.getChannel().authority())
                .build();
        return  status;
    }
}