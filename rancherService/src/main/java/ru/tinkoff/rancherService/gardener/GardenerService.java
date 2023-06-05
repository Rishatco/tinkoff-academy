package ru.tinkoff.rancherService.gardener;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.rancherService.field.Field;
import ru.tinkoff.rancherService.field.FieldService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GardenerService {

    private final GardenerRepository gardenerRepository;
    private final FieldService fieldService;

    public void delete(Long id) {
        gardenerRepository.deleteById(id);
    }

    public Gardener getById(Long id) {
        return gardenerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot find gardener with id = " + id));
    }

    public List<Gardener> getAll() {
        return gardenerRepository.findAll();
    }

    public Gardener create(GardenerDTO gardenerDTO) {
        Gardener creatingGardener = new Gardener();

        creatingGardener.setPhone(gardenerDTO.getPhone());
        creatingGardener.setEmail(gardenerDTO.getEmail());
        creatingGardener.setFirstName(gardenerDTO.getFirstName());
        creatingGardener.setLastName(gardenerDTO.getLastName());

        gardenerRepository.save(creatingGardener);

        List<Field> fields = gardenerDTO.getFields().stream()
                .map(fieldDto -> fieldService.create(fieldDto, creatingGardener.getId()))
                .toList();

        creatingGardener.setFields(fields);
        return creatingGardener;
    }

    public Gardener update(Long id, GardenerDTO gardenerDTO) {
        Gardener updatingGardener = getById(id);

        updatingGardener.setPhone(gardenerDTO.getPhone());
        updatingGardener.setEmail(gardenerDTO.getEmail());
        updatingGardener.setFirstName(gardenerDTO.getFirstName());
        updatingGardener.setLastName(gardenerDTO.getLastName());

        return gardenerRepository.save(updatingGardener);
    }
}
