package ru.tinkoff.landscapeService.client;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.landscapeService.clientType.ClientType;
import ru.tinkoff.landscapeService.clientType.ClientTypeService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientTypeService clientTypeService;

    /**
     * @param id - user id
     * @return Client with given id
     */

    public Client getById(UUID id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find client with id = " + id));
    }

    /**
     * @param offset - page's number
     * @param limit - client count on one page
     * @return page with clients
     */

    public Page<Client> getAll(Integer offset, Integer limit){
        return clientRepository.findAll(PageRequest.of(offset, limit));
    }

    /**
     *  Delete client with given id
     * @param id - client id
     */

    public void deleteById(UUID id){
        clientRepository.deleteById(id);
    }


    /**
     * Save new client
     * @param clientDTO - client data
     * @return Client with given information
     */
    public Client save(ClientDTO clientDTO){
        Client creatingClient = new Client();
        ClientType clientType = clientTypeService.getById(clientDTO.getClientType());
        creatingClient.setClientType(clientType);
        creatingClient.setEmail(clientDTO.getEmail());
        creatingClient.setLogin(clientDTO.getLogin());
        creatingClient.setPhone(clientDTO.getPhone());
        creatingClient.setLongitude(clientDTO.getLongitude());
        creatingClient.setLatitude(clientDTO.getLongitude());

        return clientRepository.save(creatingClient);
    }

    /**
     * Update client data
     * @param clientDTO - new client data
     * @param id - updating client id
     * @return - updated client
     */
    public Client update(ClientDTO clientDTO, UUID id){
        Client updatingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find client with id = " + id));

        ClientType clientType = clientTypeService.getById(clientDTO.getClientType());
        updatingClient.setClientType(clientType);
        updatingClient.setEmail(clientDTO.getEmail());
        updatingClient.setLogin(clientDTO.getLogin());
        updatingClient.setPhone(clientDTO.getPhone());
        updatingClient.setLatitude(updatingClient.getLatitude());
        updatingClient.setLongitude(updatingClient.getLongitude());

        return clientRepository.save(updatingClient);
    }
}
