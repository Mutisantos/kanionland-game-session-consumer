package com.kanionland.game.application.adapters.output.persistence.mapper;

import com.kanionland.game.application.adapters.output.persistence.documents.GameSessionEntry;
import com.kanionland.game.domain.model.GameSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameSessionDocumentMapper {

    GameSessionEntry toDocument(GameSession gameSession);

    GameSession toDomain(GameSessionEntry gameSessionEntry);

}
