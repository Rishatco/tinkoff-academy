package ru.tinkoff.rancherService.field;

import liquibase.pro.packaged.N;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.rancherService.gardener.Gardener;
import ru.tinkoff.rancherService.gardener.GardenerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;
    private final GardenerRepository gardenerRepository;

    public Field create(FieldDTO fieldDTO, Long gardenedId) {
        Field creatingField = new Field();
        creatingField.setLongitude(fieldDTO.getLongitude());
        creatingField.setLatitude(fieldDTO.getLatitude());
        creatingField.setArea(fieldDTO.getArea());
        creatingField.setAddress(fieldDTO.getAddress());

        Gardener gardener = gardenerRepository.findById(gardenedId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot find gardener with  id="+gardenedId));
        creatingField.setGardener(gardener);

        return fieldRepository.save(creatingField);
    }

    public List<Field> getAll(){
        return fieldRepository.findAll();
    }

    public Field getById(Long id){
        return fieldRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot find field with id=" + id));
    }

    public void delete(Long id){
        fieldRepository.deleteById(id);
    }

    public Field update(Long id, FieldDTO fieldDTO){
        Field updatingField = getById(id);
        updatingField.setLongitude(fieldDTO.getLongitude());
        updatingField.setLatitude(fieldDTO.getLatitude());
        updatingField.setArea(fieldDTO.getArea());
        updatingField.setAddress(fieldDTO.getAddress());
        return fieldRepository.save(updatingField);
    }
}
