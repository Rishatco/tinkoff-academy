package ru.tinkoff.landscapeService.client;

import io.micrometer.core.annotation.Timed;
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

    @Timed
    @GetMapping("/{id}")
    public User getById(@PathVariable UUID id){
        return userService.getById(id);
    }

    @Timed
    @GetMapping
    public Page<User> getPage(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer size){
        return userService.getPage(page, size);
    }

    @Timed
    @PostMapping
    public User create(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @Timed
    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody UserDTO userDTO){
        return userService.update(userDTO, id);
    }
}
