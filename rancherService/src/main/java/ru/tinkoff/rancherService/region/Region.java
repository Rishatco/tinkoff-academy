package ru.tinkoff.rancherService.region;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
@Data
@Getter
@Setter
public class Region {
    @Id
    private String id;
    private UUID subId;
    private Double latitude;
    private Double longitude;
    private Double area;
    private List<String> jobs;
}
