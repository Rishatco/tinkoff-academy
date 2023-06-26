package ru.tinkoff.rancherService.gardener;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gardeners")
@RequiredArgsConstructor
public class GardenerController {

    private final GardenerService gardenerService;

    @GetMapping
    public List<Gardener> getAll(){
        return gardenerService.getAll();
    }

    @GetMapping("/{id}")
    public Gardener getById(@PathVariable Long id){
        return  gardenerService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        gardenerService.delete(id);
    }

    @PostMapping
    public Gardener create(@RequestBody GardenerDTO gardenerDTO){
        return gardenerService.create(gardenerDTO);
    }

    @PutMapping("/{id}")
    public Gardener update(@PathVariable Long id, @RequestBody GardenerDTO gardenerDTO){
        return gardenerService.update(id, gardenerDTO);
    }
}
