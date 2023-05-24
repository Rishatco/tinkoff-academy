package ru.tinkoff.rancherService.region;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tinkoff.rancherService.landscape.client.Client;

@Data
@AllArgsConstructor
public class Profile {

    private Region region;
    private Client client;
}
