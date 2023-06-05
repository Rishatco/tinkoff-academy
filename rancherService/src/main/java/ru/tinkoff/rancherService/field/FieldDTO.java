package ru.tinkoff.rancherService.field;

import lombok.Data;
import org.locationtech.jts.geom.Geometry;

@Data
public class FieldDTO {


    private Long gardenerId;

    private String address;

    private Double latitude;

    private Double longitude;

    private Geometry area;
}
