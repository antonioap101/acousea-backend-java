package com.acousea.backend.core.users.application.http;

import com.acousea.backend.core.shared.application.services.authentication.SessionService;
import com.acousea.backend.core.shared.domain.httpWrappers.Command;
import com.acousea.backend.core.shared.domain.httpWrappers.ApiResult;
import jakarta.servlet.http.HttpSession;

public class LogoutUserCommand extends Command<Void, Boolean> {

    SessionService sessionService;
    HttpSession session;

    public LogoutUserCommand(SessionService sessionService, HttpSession session) {
        this.sessionService = sessionService;
        this.session = session;
    }

    @Override
    public ApiResult<Boolean> execute(Void none) {

        String sessionId = session.getId();
        if (sessionService.isAuthenticated(sessionId)) {
            sessionService.deleteSession(sessionId);
            return ApiResult.success(true);
        } else {
            return ApiResult.fail(401, "Invalid password");
        }
    }
}
