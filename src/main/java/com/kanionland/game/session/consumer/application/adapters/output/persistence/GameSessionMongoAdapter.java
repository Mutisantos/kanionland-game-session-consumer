package com.kanionland.game.session.consumer.application.adapters.output.persistence;

import com.kanionland.game.session.consumer.application.adapters.output.persistence.documents.GameSessionEntry;
import com.kanionland.game.session.consumer.application.adapters.output.persistence.mapper.GameSessionDocumentMapper;
import com.kanionland.game.session.consumer.application.adapters.output.persistence.repository.GameSessionMongoRepository;
import com.kanionland.game.session.consumer.application.ports.output.GameSessionPersistencePort;
import com.kanionland.game.session.consumer.infrastructure.input.jms.GameSessionMessage;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameSessionMongoAdapter implements GameSessionPersistencePort {

  private final GameSessionMongoRepository gameSessionRepository;
  private final GameSessionDocumentMapper mapper;

  @Override
  public GameSessionMessage save(GameSessionMessage gameSession) {
    GameSessionEntry entity = mapper.toDocument(gameSession);
    final GameSessionEntry savedEntry = gameSessionRepository.save(entity);
    return mapper.toDomain(savedEntry);
  }

  @Override
  public Optional<GameSessionMessage> findById(UUID id) {
    return gameSessionRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public void delete(UUID id) {
    gameSessionRepository.deleteById(id);
  }
}
