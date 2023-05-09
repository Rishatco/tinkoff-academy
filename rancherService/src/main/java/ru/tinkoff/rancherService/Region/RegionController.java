package ru.tinkoff.rancherService.Region;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranchers")
public class RegionController {

    private final RegionService regionService;

    @Timed
    @PostMapping
    public Profile save(@RequestBody RegionDTO regionDTO) {
        return regionService.save(regionDTO);
    }

    @Timed
    @GetMapping
    public List<Profile> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer size) {
        return regionService.getAll(page, size);
    }

    @Timed
    @GetMapping("/{id}")
    public Profile getById(@PathVariable String id) {
        return regionService.getById(id);
    }

    @Timed
    @PutMapping("/{id}")
    public Profile update(@PathVariable String id, @RequestBody RegionDTO regionDTO) {
        return regionService.update(id, regionDTO);
    }

    @Timed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        regionService.delete(id);
    }
}
