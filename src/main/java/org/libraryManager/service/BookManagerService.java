package org.libraryManager.service;

import org.libraryManager.data.models.Book;
import org.libraryManager.data.models.User;
import org.libraryManager.dtos.request.*;

public interface BookManagerService {
    User register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(LogoutRequest logoutRequest);
    User findAccountBelongingTo(String username);
    Book addBook(AddBookRequest addBookRequest);
    Book findBook(FindBookRequest findBookRequest);
    void removeBook(RemoveBookRequest removeBookRequest);
}
