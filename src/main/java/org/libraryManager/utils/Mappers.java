package org.libraryManager.utils;

import org.libraryManager.data.models.User;

public class Mappers {
    public static User map(String userId, String username, String password){
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
