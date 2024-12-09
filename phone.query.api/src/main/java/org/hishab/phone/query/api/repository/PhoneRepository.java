package org.hishab.phone.query.api.repository;

import org.hishab.phone.query.api.entity.PhoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends MongoRepository<PhoneEntity, String> {
    Page<PhoneEntity> findByCreatedAtGreaterThan(Instant createdAt, Pageable pageable);

    @Query("{ 'phone_number' : ?0 }")
    List<PhoneEntity> findByPhone_number(String phoneNumber);
}
