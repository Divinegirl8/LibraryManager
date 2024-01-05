package org.libraryManager.service;

import org.libraryManager.data.models.Book;
import org.libraryManager.data.models.Transaction;
import org.libraryManager.data.models.User;
import org.libraryManager.dtos.request.*;

import java.util.List;

public interface BookManagerService {
    User register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(LogoutRequest logoutRequest);
    User findAccountBelongingTo(String username);
    Book addBook(AddBookRequest addBookRequest);
    Book findBook(FindBookRequest findBookRequest);
    void removeBook(RemoveBookRequest removeBookRequest);
    List<Book> listOfBooks();
    List<Book> findListOfBooksByAuthor(String author);
    void deleteAllBooks();
    void deleteAllBooksByAuthor(String author);
    Transaction checkIn(CheckInRequest checkInRequest);
}
