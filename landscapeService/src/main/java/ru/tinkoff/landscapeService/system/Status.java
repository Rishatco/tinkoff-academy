package ru.tinkoff.landscapeService.system;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Status {

    private String artifact;
    private String name;
    private String group;
    private String version;
    private String host;
    private String status;
}
