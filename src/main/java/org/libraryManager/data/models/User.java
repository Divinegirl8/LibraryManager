package org.libraryManager.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class User {
    @Id
    private String userId;
    private String username;
    private String password;
    private boolean Login;
}
