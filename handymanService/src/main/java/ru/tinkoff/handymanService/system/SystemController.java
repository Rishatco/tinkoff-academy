package ru.tinkoff.handymanService.system;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {

    private final BuildProperties buildProperties;

    /**
     * check service liveness
     *
     * @return {@link HttpStatus#OK} status
     */
    @GetMapping("/liveness")
    public void getLiveness() {
    }

    /**
     * check service readiness
     *
     * @return service name and service status
     */
    @GetMapping("/readiness")
    public Map<String, String> getReadiness() {
        Map<String, String> answer = new HashMap<>();
        answer.put(buildProperties.getName(), "OK");
        return answer;
    }
}
