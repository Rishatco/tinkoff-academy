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
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getById(@PathVariable UUID id){
        return userService.getById(id);
    }

    @GetMapping
    public Page<User> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer size){
        return userService.getAll(page, size);
    }

    @PostMapping
    public User create(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody UserDTO userDTO){
        return userService.update(userDTO, id);
    }
}
