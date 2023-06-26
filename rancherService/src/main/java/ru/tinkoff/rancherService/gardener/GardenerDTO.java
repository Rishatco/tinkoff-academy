package ru.tinkoff.rancherService.gardener;

import lombok.Data;
import ru.tinkoff.rancherService.field.FieldDTO;

import java.util.List;

@Data
public class GardenerDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private List<FieldDTO> fields;
}
