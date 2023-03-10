package ru.tinkoff.handymanService.status;

import com.google.protobuf.Empty;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.VersionResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class StatusServiceTest {

    @Autowired
    private StatusServer statusServer;
    @Autowired
    private BuildProperties buildProperties;

    @Test
    void getVersionTest() throws Exception {
        VersionResponse versionResponse = VersionResponse.newBuilder()
                .setVersion(buildProperties.getVersion())
                .setArtifact(buildProperties.getArtifact())
                .setGroup(buildProperties.getGroup())
                .setName(buildProperties.getName())
                .build();

        StreamRecorder<VersionResponse> responseObserver = StreamRecorder.create();
        statusServer.getVersion(Empty.getDefaultInstance(), responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS))
            Assertions.fail("The call did not terminate in time");

        Assertions.assertNull(responseObserver.getError());
        List<VersionResponse> result = responseObserver.getValues();
        Assertions.assertEquals(1, result.size());
        VersionResponse actualResponse = result.get(0);
        Assertions.assertEquals(versionResponse, actualResponse);

    }

    @Test
    void getReadinessTest() throws Exception {
        ReadinessResponse readinessResponse = ReadinessResponse.newBuilder()
                .setStatus("OK").build();

        StreamRecorder<ReadinessResponse> responseObserver = StreamRecorder.create();
        statusServer.getReadiness(Empty.getDefaultInstance(), responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS))
            Assertions.fail("The call did not terminate in time");

        Assertions.assertNull(responseObserver.getError());
        List<ReadinessResponse> result = responseObserver.getValues();
        Assertions.assertEquals(1, result.size());
        ReadinessResponse actualResponse = result.get(0);
        Assertions.assertEquals(readinessResponse, actualResponse);

    }

}
