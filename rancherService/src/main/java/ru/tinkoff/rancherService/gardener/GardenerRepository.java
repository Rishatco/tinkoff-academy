package ru.tinkoff.rancherService.gardener;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenerRepository extends JpaRepository<Gardener, Long> {
}
