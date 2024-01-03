package org.libraryManager.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.libraryManager.data.repositories.BookRepository;
import org.libraryManager.data.repositories.TransactionRepository;
import org.libraryManager.data.repositories.UserRepository;
import org.libraryManager.dtos.request.LoginRequest;
import org.libraryManager.dtos.request.LogoutRequest;
import org.libraryManager.dtos.request.RegisterRequest;
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




}