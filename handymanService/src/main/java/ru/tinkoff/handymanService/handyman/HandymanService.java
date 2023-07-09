package ru.tinkoff.handymanService.handyman;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.handymanService.landscape.сlient.Client;
import ru.tinkoff.handymanService.landscape.сlient.ClientDTO;
import ru.tinkoff.handymanService.landscape.LandscapeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HandymanService {

    private static final long HANDYMAN_TYPE_ID = 1;

    private final HandymanRepository handymanRepository;
    private final LandscapeService landscapeService;


    /**
     * @param handymanDTO - handynam data
     * @return created profile
     */
    public Profile create(HandymanDTO handymanDTO){
        ClientDTO creatingClient = convertHandymanDTOtoClient(handymanDTO);
        Client client = landscapeService.save(creatingClient);
        Handyman handyman = new Handyman();
        handyman.setSkills(handymanDTO.getSkills());
        handyman.setSubId(client.getId());
        handyman.setLongitude(client.getLongitude());
        handyman.setLatitude(client.getLatitude());

        handymanRepository.insert(handyman);

        return new Profile(handyman, client);
    }

    /**
     * @param id
     * @return profile with given id
     */
    public Profile getById(String id){
        Handyman handyman = handymanRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot find handyman with given id" + id));
        Client client = landscapeService.getById(handyman.getSubId());
        return new Profile(handyman, client);
    }

    /**
     * @return all Handyman from bd
     */
    public List<Profile> getPage(Integer page, Integer size){
        Page<Handyman> handymen = handymanRepository.findAll(PageRequest.of(page, size));
        return  handymen.stream()
                .map(handyman -> new Profile(handyman, landscapeService.getById(handyman.getSubId())))
                .toList();
    }

    /**
     * @param id
     * @param handymanDTO new handyman data
     * @return updated Profile
     */

    public Profile update(String id, HandymanDTO handymanDTO){
        Handyman handyman = handymanRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot find handyman with given id" + id));
        ClientDTO clientDTO = convertHandymanDTOtoClient(handymanDTO);
        Client updatedClient = landscapeService.update(handyman.getSubId(), clientDTO);

        handyman.setLatitude(handymanDTO.getLatitude());
        handyman.setLongitude(handymanDTO.getLongitude());
        handyman.setSkills(handymanDTO.getSkills());

        handymanRepository.save(handyman);
        return new Profile(handyman, updatedClient);
    }

    /**
     *  Delete Handyman
     * @param id
     */
    public void delete(String id){
        Handyman handyman = handymanRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot find handyman with given id" + id));
        landscapeService.delete(handyman.getSubId());
        handymanRepository.delete(handyman);
    }

    private ClientDTO convertHandymanDTOtoClient(HandymanDTO handymanDTO){
        ClientDTO creatingClient = new ClientDTO();
        creatingClient.setClientType(HANDYMAN_TYPE_ID);
        creatingClient.setLatitude(handymanDTO.getLatitude());
        creatingClient.setPhone(handymanDTO.getPhone());
        creatingClient.setEmail(handymanDTO.getEmail());
        creatingClient.setLongitude(handymanDTO.getLongitude());
        creatingClient.setLogin(handymanDTO.getLogin());
        return creatingClient;
    }
}
