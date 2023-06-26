package ru.tinkoff.handymanService.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("handymanUsers")
public class HandymanUserController {

    private final HandymanUserService handymanUserService;

    @GetMapping
    public List<HandymanUser> getAll() {
        return handymanUserService.getAll();
    }

    @GetMapping("/{id}")
    public HandymanUser getById(@PathVariable Long id) {
        return handymanUserService.getById(id);
    }

    @PostMapping
    public HandymanUser create(@RequestBody HandymanUserDTO handymanUserDTO) {
        return handymanUserService.create(handymanUserDTO);
    }

    @PutMapping("/{id}")
    public HandymanUser update(@PathVariable Long id, @RequestBody HandymanUserDTO handymanUserDTO) {
        return handymanUserService.update(handymanUserDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id) {
        handymanUserService.delete(id);
    }

    @PostMapping("/photo/{id}")
    public HandymanUser uploadPhoto(@PathVariable Long id, @RequestParam MultipartFile photo) {
        return handymanUserService.setPhoto(id, photo);
    }

}
