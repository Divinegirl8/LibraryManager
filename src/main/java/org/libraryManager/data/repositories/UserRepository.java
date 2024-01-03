package org.libraryManager.data.repositories;

import org.libraryManager.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findUserByUsername(String username);
}
