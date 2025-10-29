package com.kanionland.game.session.consumer.application.adapters.output.persistence.repository;

import com.kanionland.game.session.consumer.application.adapters.output.persistence.documents.GameSessionDocument;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionMongoRepository extends MongoRepository<GameSessionDocument, UUID> {

}
