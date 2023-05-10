package ru.tinkoff.handymanService.landscape;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.handymanService.landscape.Client.Client;
import ru.tinkoff.handymanService.landscape.Client.ClientDTO;

import java.util.UUID;

@FeignClient(value = "client-service", url = "${application.landscape.url}")
public interface LandscapeClient {

    @PostMapping("/client")
    Client create(@RequestBody ClientDTO clientDTO);

    @GetMapping("/client/{id}")
    Client getById(@PathVariable UUID id);

    @PutMapping("/client/{id}")
    Client update(@PathVariable UUID id, @RequestBody ClientDTO clientDTO);

    @DeleteMapping("/users/{id}")
    void delete(@PathVariable UUID id);
}
