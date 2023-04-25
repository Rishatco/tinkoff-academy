package ru.tinkoff.landscapeService.client;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public Client findById(@PathVariable UUID id){
        return clientService.getById(id);
    }

    @GetMapping
    public Page<Client> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer size){
        return clientService.getAll(page, size);
    }

    @PostMapping
    public Client create(@RequestBody ClientDTO clientDTO){
        return clientService.save(clientDTO);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable UUID id, @RequestBody ClientDTO clientDTO){
        return clientService.update(clientDTO, id);
    }
}
