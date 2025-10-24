package com.kanionland.game.application.adapters.output.persistence;

import com.kanionland.game.application.adapters.output.persistence.documents.GameSessionEntry;
import com.kanionland.game.application.adapters.output.persistence.mapper.GameSessionDocumentMapper;
import com.kanionland.game.application.ports.output.GameSessionPersistencePort;
import com.kanionland.game.application.adapters.output.persistence.documents.GameSessionJpaEntity;
import com.kanionland.game.application.adapters.output.persistence.repository.GameSessionMongoRepository;
import com.kanionland.game.domain.model.GameSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GameSessionJpaAdapter implements GameSessionPersistencePort {

    private final GameSessionMongoRepository gameSessionRepository;
    private final GameSessionDocumentMapper mapper;

    @Override
    public GameSession save(GameSession gameSession) {
        GameSessionEntry entity = mapper.toDocument(gameSession);
        final GameSessionEntry savedEntry = gameSessionRepository.save(entity);
        return mapper.toDomain(savedEntry);
    }

    @Override
    public Optional<GameSession> findById(UUID id) {
        return gameSessionRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void delete(UUID id) {
        gameSessionRepository.deleteById(id);
    }
}
