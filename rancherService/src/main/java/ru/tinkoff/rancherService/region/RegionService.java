package ru.tinkoff.rancherService.region;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.rancherService.landscape.client.Client;
import ru.tinkoff.rancherService.landscape.client.ClientDTO;
import ru.tinkoff.rancherService.landscape.LandscapeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private static final long RANCHER_TYPE_ID = 2;

    private final LandscapeService landscapeService;
    private final RegionRepository regionRepository;

    /**
     * @param regionDTO region's data
     * @return created Profile
     */
    public Profile save(RegionDTO regionDTO) {
        ClientDTO creatingClientDto = createClientByRegionDTO(regionDTO);
        Client client = landscapeService.save(creatingClientDto);
        Region region = mapRegionDTOtoRegion(regionDTO, client);
        regionRepository.insert(region);

        return new Profile(region, client);
    }

    /**
     * @param id
     * @return Profile with region with given id
     */
    public Profile getById(String id) {
        Region region = regionRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "cannot find region with id " + id));
        Client client = landscapeService.getById(region.getSubId());
        return new Profile(region, client);
    }

    /**
     * @return all Rancher Profiles
     */
    public List<Profile> getPage(Integer page, Integer size) {
        Page<Region> regions = regionRepository.findAll(PageRequest.of(page, size));
        return regions.stream().map(region -> new Profile(region, landscapeService.getById(region.getSubId()))).toList();
    }

    /**
     *
     * @param id updating region
     * @param regionDTO - region's data
     * @return updated Profile
     */
    public Profile update(String id, RegionDTO regionDTO){
        Region region = regionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "cannot find region with id " + id));
        ClientDTO clientDTO = createClientByRegionDTO(regionDTO);
        Client updatedClient = landscapeService.update(region.getSubId(), clientDTO);
        region = mapRegionDTOtoRegion(regionDTO, updatedClient);
        region.setId(id);
        regionRepository.save(region);
        return new Profile(region, updatedClient);
    }

    /**
     *  delete region and client
     * @param id
     */
    public void delete(String id){
        Region region = regionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "cannot find region with id " + id));
        landscapeService.delete(region.getSubId());
        regionRepository.delete(region);
    }

    private Region mapRegionDTOtoRegion(RegionDTO regionDTO, Client client) {
        Region region = new Region();
        region.setJobs(regionDTO.getJobs());
        region.setArea(region.getArea());
        region.setLongitude(region.getLongitude());
        region.setLatitude(regionDTO.getLatitude());
        region.setSubId(client.getId());
        return region;
    }

    private ClientDTO createClientByRegionDTO(RegionDTO regionDTO) {
        ClientDTO createdClient = new ClientDTO();
        createdClient.setClientType(RANCHER_TYPE_ID);
        createdClient.setPhone(regionDTO.getPhone());
        createdClient.setLogin(regionDTO.getLogin());
        createdClient.setEmail(regionDTO.getEmail());
        createdClient.setLongitude(regionDTO.getLongitude());
        createdClient.setLatitude(regionDTO.getLatitude());
        return createdClient;
    }
}
