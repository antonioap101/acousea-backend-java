package com.acousea.backend.core.communicationSystem.application.ports;

import com.acousea.backend.core.communicationSystem.domain.RockBlockMessage;
import com.acousea.backend.core.shared.application.ports.IRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface RockblockMessageRepository extends IRepository<RockBlockMessage, UUID> {
    List<RockBlockMessage> getPaginatedMessages(int page, int rowsPerPage);
}
