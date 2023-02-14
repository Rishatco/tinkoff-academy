package ru.tinkoff.landscapeService.system;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
@NoArgsConstructor
public class SystemController {


    @GetMapping("/liveness")
    public HttpStatus isAlive(){
        return HttpStatus.OK;
    }
}
