package com.kanionland.game.application.services;

import com.kanionland.game.application.ports.input.GameSessionService;
import com.kanionland.game.application.ports.output.GameSessionPersistencePort;
import com.kanionland.game.domain.model.GameSession;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GameSessionServiceImpl implements GameSessionService {
    
    private final GameSessionPersistencePort gameSessionPersistencePort;
    
    public GameSessionServiceImpl(GameSessionPersistencePort gameSessionPersistencePort) {
        this.gameSessionPersistencePort = gameSessionPersistencePort;
    }
    
    @Override
    public GameSession startSession(GameSession gameSession) {
        return gameSessionPersistencePort.save(gameSession);
    }

    @Override
    public GameSession endSession(GameSession gameSession) {
        return gameSessionPersistencePort.findById(gameSession.getId())
            .map(endingSession -> {
                endingSession.endSession();
                return gameSessionPersistencePort.save(endingSession);
            })
            .orElseThrow(() -> new RuntimeException("Game session not found with id: " + gameSession.getId()));
    }

    @Override
    public GameSession updateSession(GameSession gameSession) {
        return gameSessionPersistencePort.findById(gameSession.getId())
            .map(gameSessionPersistencePort::save)
            .orElseThrow(() -> new RuntimeException("Game session not found with id: " + gameSession.getId()));
    }
    
    @Override
    public Optional<GameSession> getSession(UUID sessionId) {
        return gameSessionPersistencePort.findById(sessionId);
    }
}
