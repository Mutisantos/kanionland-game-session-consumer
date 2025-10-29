package com.kanionland.game.session.consumer.application.adapters.output.persistence;

import com.kanionland.game.session.consumer.application.adapters.output.persistence.documents.GameSessionDocument;
import com.kanionland.game.session.consumer.application.adapters.output.persistence.documents.GameSessionEntry;
import com.kanionland.game.session.consumer.application.adapters.output.persistence.mapper.GameSessionDocumentMapper;
import com.kanionland.game.session.consumer.application.adapters.output.persistence.repository.GameSessionMongoRepository;
import com.kanionland.game.session.consumer.application.ports.output.GameSessionPersistencePort;
import com.kanionland.game.session.consumer.domain.model.GameSessionActionModel;
import com.kanionland.game.session.consumer.domain.model.GameSessionModel;
import com.kanionland.game.session.consumer.infrastructure.input.jms.GameSessionMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameSessionMongoAdapter implements GameSessionPersistencePort {

  private final GameSessionMongoRepository repository;
  private final GameSessionDocumentMapper mapper;

  @Override
  public GameSessionActionModel save(GameSessionMessage message) {
    GameSessionEntry entry = mapper.toDocument(message);

    return repository.findById(message.getSessionId())
        .map(session -> {
          // Update existing session
          session.getMessages().add(entry);
          session.setLastUpdated(LocalDateTime.now());
          GameSessionDocument updated = repository.save(session);
          return mapper.toDomain(entry);
        })
        .orElseGet(() -> {
          // Create new session
          GameSessionDocument newSession = GameSessionDocument.builder()
              .sessionId(message.getSessionId())
              .messages(new ArrayList<>(List.of(entry)))
              .createdAt(LocalDateTime.now())
              .lastUpdated(LocalDateTime.now())
              .build();
          GameSessionDocument saved = repository.save(newSession);
          return mapper.toDomain(entry);
        });

  }

  @Override
  public GameSessionModel findById(UUID id) {
    return repository.findById(id)
        .map(mapper::toSessionModel)
        .orElse(null);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }
}
