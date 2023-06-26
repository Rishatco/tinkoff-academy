package ru.tinkoff.landscapeService.client_type;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    /**
     *
     * @param id - clientType id
     * @return clientType with given id
     */
    public UserType getById(Long id){
       return userTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find clienttype with id = " + id));
    }
}
