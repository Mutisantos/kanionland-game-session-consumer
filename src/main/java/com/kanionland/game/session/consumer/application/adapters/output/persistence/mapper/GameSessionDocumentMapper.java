package com.kanionland.game.session.consumer.application.adapters.output.persistence.mapper;

import com.kanionland.game.session.consumer.application.adapters.output.persistence.documents.GameSessionEntry;
import com.kanionland.game.session.consumer.infrastructure.input.jms.GameSessionMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameSessionDocumentMapper {

  GameSessionEntry toDocument(GameSessionMessage gameSession);

  GameSessionMessage toDomain(GameSessionEntry gameSessionEntry);

}
