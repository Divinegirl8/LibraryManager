package org.libraryManager.service;

import org.libraryManager.data.models.User;
import org.libraryManager.data.repositories.UserRepository;
import org.libraryManager.dtos.request.LoginRequest;
import org.libraryManager.dtos.request.LogoutRequest;
import org.libraryManager.dtos.request.RegisterRequest;
import org.libraryManager.exceptions.InvalidCredentialsException;
import org.libraryManager.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.libraryManager.utils.Mappers.*;

@Service
public class BookManagerServiceImpl implements BookManagerService{
    @Autowired
    BookService bookService;
    @Autowired
    UserRepository userRepository;


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

    private boolean userExist(String username){
        User user = userRepository.findUserByUsername(username);
        return user != null;
    }
}
