package ru.tinkoff.handymanService.handyman;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HandymanRepository extends MongoRepository<Handyman, String> {
}
