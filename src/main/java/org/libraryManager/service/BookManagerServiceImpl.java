package org.libraryManager.service;

import org.libraryManager.data.models.Book;
import org.libraryManager.data.models.Transaction;
import org.libraryManager.data.models.User;
import org.libraryManager.data.repositories.BookRepository;
import org.libraryManager.data.repositories.TransactionRepository;
import org.libraryManager.data.repositories.UserRepository;
import org.libraryManager.dtos.request.*;
import org.libraryManager.exceptions.InvalidCredentialsException;
import org.libraryManager.exceptions.UserExistException;
import org.libraryManager.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.libraryManager.utils.Mappers.*;

@Service
public class BookManagerServiceImpl implements BookManagerService{
    @Autowired
    BookService bookService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionService transactionService;


    @Override
    public User register(RegisterRequest registerRequest) {
        if (userExist(registerRequest.getUsername())) throw  new UserExistException(registerRequest.getUsername() + " already exist");
        User user = map("UID"+(userRepository.count()+1),registerRequest.getUsername(),registerRequest.getPassword());
        userRepository.save(user);
        return user;
    }

    @Override
    public void login(LoginRequest loginRequest) {
        User user = userRepository.findUserByUsername(loginRequest.getUsername());

        if (!userExist(loginRequest.getUsername())) throw new InvalidCredentialsException();
        if (!loginRequest.getPassword().equals(user.getPassword())) throw new InvalidCredentialsException();

        user.setLogin(true);
        userRepository.save(user);
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        User user = userRepository.findUserByUsername(logoutRequest.getUsername());

        if (!userExist(logoutRequest.getUsername())) throw new InvalidCredentialsException();
        if (!logoutRequest.getPassword().equals(user.getPassword())) throw new InvalidCredentialsException();

        user.setLogin(false);
        userRepository.save(user);
    }

    @Override
    public User findAccountBelongingTo(String username) {
        User user = userRepository.findUserByUsername(username);

        if (user == null) throw new UserNotFoundException(username + " not found");

        return user;
    }

    @Override
    public Book addBook(AddBookRequest addBookRequest) {
        return bookService.addBook("BID" + (bookRepository.count()+1), addBookRequest.getTitle(), addBookRequest.getAuthor(), addBookRequest.getIsbn());
    }

    @Override
    public Book findBook(FindBookRequest findBookRequest) {
        return bookService.findBook(findBookRequest.getAuthor(),findBookRequest.getTitle());
    }

    @Override
    public void removeBook(RemoveBookRequest removeBookRequest) {
     bookService.removeBook(removeBookRequest.getAuthor(),removeBookRequest.getTitle());
    }

    @Override
    public List<Book> listOfBooks() {
        return bookService.listOfBooks();
    }

    @Override
    public List<Book> findListOfBooksByAuthor(String author) {
        return bookService.findListOfBooksByAuthor(author);
    }

    @Override
    public void deleteAllBooks() {
      bookService.deleteAllBooks();
    }

    @Override
    public void deleteAllBooksByAuthor(String author) {
     bookService.deleteAllBooksByAuthor(author);
    }

    @Override
    public Transaction checkIn(CheckInRequest checkInRequest) {
        return transactionService.checkIn("TID" + (transactionRepository.count()+1), checkInRequest.getUserId(), checkInRequest.getTitle(), checkInRequest.getAuthor(), checkInRequest.getDueDate(), LocalDateTime.now(), checkInRequest.getAmountCharge());
    }

    @Override
    public Transaction checkOut(CheckoutRequest checkoutRequest) {
        return transactionService.checkOut(checkoutRequest.getTransactionId(), checkoutRequest.getTitle(), checkoutRequest.getAuthor(),checkoutRequest.getDateReturned());
    }

    private boolean userExist(String username){
        User user = userRepository.findUserByUsername(username);
        return user != null;
    }
}
