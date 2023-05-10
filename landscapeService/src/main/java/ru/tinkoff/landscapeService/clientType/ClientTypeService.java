package ru.tinkoff.landscapeService.clientType;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ClientTypeService {

    private final ClientTypeRepository clientTypeRepository;

    /**
     *
     * @param id - clientType id
     * @return clientType with given id
     */
    public ClientType getById(Long id){
       return clientTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find clienttype with id = " + id));
    }
}
