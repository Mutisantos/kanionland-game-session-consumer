package com.kanionland.game.application.adapters.output.persistence.repository;

import com.kanionland.game.application.adapters.output.persistence.documents.GameSessionEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameSessionMongoRepository extends MongoRepository<GameSessionEntry, UUID> {
}
