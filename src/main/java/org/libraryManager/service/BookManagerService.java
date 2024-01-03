package org.libraryManager.service;

import org.libraryManager.data.models.User;
import org.libraryManager.dtos.request.LoginRequest;
import org.libraryManager.dtos.request.LogoutRequest;
import org.libraryManager.dtos.request.RegisterRequest;

public interface BookManagerService {
    User register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(LogoutRequest logoutRequest);
    User findAccountBelongingTo(String username);
}
