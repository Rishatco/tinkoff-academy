package ru.tinkoff.landscapeService.client;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client findById(UUID id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find client with id = " + id));
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public void deleteById(UUID id){
        clientRepository.deleteById(id);
    }


}
