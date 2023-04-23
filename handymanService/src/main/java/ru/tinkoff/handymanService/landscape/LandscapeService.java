package ru.tinkoff.handymanService.landscape;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.handymanService.landscape.Client.Client;
import ru.tinkoff.handymanService.landscape.Client.ClientDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LandscapeService {

    private static final String ERROR_MESSAGE = "Cannot connect to landscape service";

    private final LandscapeClient landscapeClient;

    public Client save(ClientDTO clientDTO){
        try {
            return landscapeClient.create(clientDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
        }

    }

    public Client update(UUID id, ClientDTO clientDTO){
        try {
            return landscapeClient.update(id, clientDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
        }
    }

    public Client getById(UUID id){
        try {
            return landscapeClient.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
        }
    }

    public void delete(UUID id){
        try {
            landscapeClient.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
        }
    }

}
