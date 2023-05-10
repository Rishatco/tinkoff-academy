package ru.tinkoff.rancherService.Region;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tinkoff.rancherService.landscape.Client.Client;

@Data
@AllArgsConstructor
public class Profile {

    private Region region;
    private Client client;
}
