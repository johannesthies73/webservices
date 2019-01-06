package de.hszg.webservices.ts05.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {

    private String id;
    private Email email;
    private String password;

    public User(Email email, String password) {
        this.email = email;
        this.password = password;
    }
}
