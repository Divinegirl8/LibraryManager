package org.libraryManager.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.libraryManager.data.repositories.BookRepository;
import org.libraryManager.data.repositories.TransactionRepository;
import org.libraryManager.data.repositories.UserRepository;
import org.libraryManager.dtos.request.*;
import org.libraryManager.exceptions.BookNotFoundException;
import org.libraryManager.exceptions.InvalidCredentialsException;
import org.libraryManager.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookManagerServiceImplTest {

    @Autowired
    BookManagerService bookManagerService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void cleanUp(){
        bookRepository.deleteAll();
        transactionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void register_User_Count_Is_One(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertEquals(1,userRepository.count());

    }

    @Test void register_User_With_Same_Username(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertThrows(UserExistException.class,()-> bookManagerService.register(registerRequest));

    }

    @Test void register_User_And_Login(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        bookManagerService.login(loginRequest);

        assertTrue(bookManagerService.findAccountBelongingTo("username").isLogin());
    }

    @Test void register_User_And_Login_With_Wrong_Username(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("name");
        loginRequest.setPassword("password");


        assertThrows(InvalidCredentialsException.class,()-> bookManagerService.login(loginRequest));
    }

    @Test void register_User_And_Login_With_Wrong_Password(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("word");


        assertThrows(InvalidCredentialsException.class,()-> bookManagerService.login(loginRequest));
    }
    @Test void register_User_Login_And_Logout(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        bookManagerService.login(loginRequest);
        assertTrue(bookManagerService.findAccountBelongingTo("username").isLogin());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("username");
        logoutRequest.setPassword("password");
        bookManagerService.logout(logoutRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

    }

    @Test void register_User_Login_And_Logout_With_Wrong_Username(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        bookManagerService.login(loginRequest);
        assertTrue(bookManagerService.findAccountBelongingTo("username").isLogin());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("name");
        logoutRequest.setPassword("password");

       assertThrows(InvalidCredentialsException.class,()->  bookManagerService.logout(logoutRequest));
    }


    @Test void register_User_Login_And_Logout_With_Wrong_Password(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        bookManagerService.login(loginRequest);
        assertTrue(bookManagerService.findAccountBelongingTo("username").isLogin());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("username");
        logoutRequest.setPassword("word");

        assertThrows(InvalidCredentialsException.class,()->  bookManagerService.logout(logoutRequest));
    }

    @Test void register_User_Login_AddBook_And_Logout(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        bookManagerService.login(loginRequest);
        assertTrue(bookManagerService.findAccountBelongingTo("username").isLogin());

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("Title");
        addBookRequest.setAuthor("Author");
        addBookRequest.setIsbn("isbn");
        bookManagerService.addBook(addBookRequest);
        assertEquals(1,bookRepository.count());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("username");
        logoutRequest.setPassword("password");
        bookManagerService.logout(logoutRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

    }


    @Test void register_User_Login_AddBook_FindBook_And_Logout(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        bookManagerService.login(loginRequest);
        assertTrue(bookManagerService.findAccountBelongingTo("username").isLogin());

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("Title");
        addBookRequest.setAuthor("Author");
        addBookRequest.setIsbn("isbn");
        bookManagerService.addBook(addBookRequest);
        assertEquals(1,bookRepository.count());

        FindBookRequest findBookRequest = new FindBookRequest();
        findBookRequest.setAuthor("Author");
        findBookRequest.setTitle("Title");

        assertNotNull(bookManagerService.findBook(findBookRequest));

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("username");
        logoutRequest.setPassword("password");
        bookManagerService.logout(logoutRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

    }


    @Test void register_User_Login_AddBook_FindBook_That_Is_Not_In_The_Library_Throws_An_Exception(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        bookManagerService.login(loginRequest);
        assertTrue(bookManagerService.findAccountBelongingTo("username").isLogin());

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("Title");
        addBookRequest.setAuthor("Author");
        addBookRequest.setIsbn("isbn");
        bookManagerService.addBook(addBookRequest);
        assertEquals(1,bookRepository.count());

        FindBookRequest findBookRequest = new FindBookRequest();
        findBookRequest.setAuthor("Author");
        findBookRequest.setTitle("Til");

        assertThrows(BookNotFoundException.class,()->bookManagerService.findBook(findBookRequest));

    }


    @Test void register_User_Login_AddBook_RemoveBook_And_Logout(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        bookManagerService.login(loginRequest);
        assertTrue(bookManagerService.findAccountBelongingTo("username").isLogin());

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("Title");
        addBookRequest.setAuthor("Author");
        addBookRequest.setIsbn("isbn");
        bookManagerService.addBook(addBookRequest);
        assertEquals(1,bookRepository.count());

       RemoveBookRequest removeBookRequest = new RemoveBookRequest();
       removeBookRequest.setAuthor("Author");
       removeBookRequest.setTitle("Title");

       bookManagerService.removeBook(removeBookRequest);

        FindBookRequest findBookRequest = new FindBookRequest();
        findBookRequest.setAuthor("Author");
        findBookRequest.setTitle("Title");

        assertThrows(BookNotFoundException.class,()->bookManagerService.findBook(findBookRequest));



        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("username");
        logoutRequest.setPassword("password");
        bookManagerService.logout(logoutRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

    }
    @Test void register_User_Login_AddBook_RemoveBook_WithWrongTitle(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        bookManagerService.register(registerRequest);
        assertFalse(bookManagerService.findAccountBelongingTo("username").isLogin());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        bookManagerService.login(loginRequest);
        assertTrue(bookManagerService.findAccountBelongingTo("username").isLogin());

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("Title");
        addBookRequest.setAuthor("Author");
        addBookRequest.setIsbn("isbn");
        bookManagerService.addBook(addBookRequest);
        assertEquals(1,bookRepository.count());

        RemoveBookRequest removeBookRequest = new RemoveBookRequest();
        removeBookRequest.setAuthor("Author");
        removeBookRequest.setTitle("Tile");
        assertThrows(BookNotFoundException.class,()->bookManagerService.removeBook(removeBookRequest));
    }



}