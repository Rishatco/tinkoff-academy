package ru.tinkoff.landscapeService.client;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.landscapeService.client_type.UserType;
import ru.tinkoff.landscapeService.client_type.UserTypeService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserTypeService userTypeService;

    /**
     * @param id - user id
     * @return Client with given id
     */

    public User getById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find client with id = " + id));
    }

    /**
     * @param offset - page's number
     * @param limit - client count on one page
     * @return page with clients
     */

    public Page<User> getPage(Integer offset, Integer limit){
        return userRepository.findAll(PageRequest.of(offset, limit));
    }

    /**
     *  Delete client with given id
     * @param id - client id
     */

    public void deleteById(UUID id){
        userRepository.deleteById(id);
    }


    /**
     * Save new client
     * @param userDTO - client data
     * @return Client with given information
     */
    public User save(UserDTO userDTO){
        User creatingUser = new User();
        UserType userType = userTypeService.getById(userDTO.getClientType());
        creatingUser.setUserType(userType);
        creatingUser.setEmail(userDTO.getEmail());
        creatingUser.setLogin(userDTO.getLogin());
        creatingUser.setPhone(userDTO.getPhone());
        creatingUser.setLongitude(userDTO.getLongitude());
        creatingUser.setLatitude(userDTO.getLongitude());

        return userRepository.save(creatingUser);
    }

    /**
     * Update client data
     * @param userDTO - new client data
     * @param id - updating client id
     * @return - updated client
     */
    public User update(UserDTO userDTO, UUID id){
        User updatingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find client with id = " + id));

        UserType userType = userTypeService.getById(userDTO.getClientType());
        updatingUser.setUserType(userType);
        updatingUser.setEmail(userDTO.getEmail());
        updatingUser.setLogin(userDTO.getLogin());
        updatingUser.setPhone(userDTO.getPhone());
        updatingUser.setLatitude(updatingUser.getLatitude());
        updatingUser.setLongitude(updatingUser.getLongitude());

        return userRepository.save(updatingUser);
    }
}
