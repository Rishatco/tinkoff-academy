package ru.tinkoff.handymanService.handyman;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/handyman")
@RequiredArgsConstructor
public class HandymanController {

    private final HandymanService handymanService;

    @Timed
    @GetMapping
    public List<Profile> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer size) {
        return handymanService.getAll(page, size);
    }

    @Timed
    @GetMapping("/{id}")
    public Profile getById(@PathVariable String id) {
        return handymanService.getById(id);
    }

    @Timed
    @PostMapping
    public Profile save(@RequestBody HandymanDTO handymanDTO) {
        return handymanService.create(handymanDTO);
    }

    @Timed
    @PutMapping("/{id}")
    public Profile update(@PathVariable String id, @RequestBody HandymanDTO handymanDTO) {
        return handymanService.update(id, handymanDTO);
    }

    @Timed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        handymanService.delete(id);
    }

}
