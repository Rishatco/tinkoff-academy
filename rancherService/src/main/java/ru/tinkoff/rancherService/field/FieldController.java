package ru.tinkoff.rancherService.field;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fields")
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;

    @GetMapping
    public List<Field> getAll() {
        return fieldService.getAll();
    }

    @GetMapping("/{id}")
    public Field getById(@PathVariable Long id) {
        return fieldService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fieldService.delete(id);
    }

    @PostMapping
    public Field create(@RequestBody FieldDTO fieldDTO, @RequestParam Long gardenerId) {
        return fieldService.create(fieldDTO, gardenerId);
    }

    @PutMapping("/{id}")
    public Field update(@PathVariable Long id, @RequestBody FieldDTO fieldDTO) {
        return fieldService.update(id, fieldDTO);
    }
}
