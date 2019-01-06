package de.hszg.webservices.ts05.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Data
@NoArgsConstructor
public class UserRepository implements Serializable {

    private Map<String, User> users;

    @PostConstruct
    public void init() {
        this.save(new User(new Email("jo@web.de"), "test"));
    }

    public Optional<User> find(String id) {
        User user = users.get(id);

        return Optional.of(user);
    }

    public Optional<User> find(Email email) {
        AtomicReference<User> user = null;

        users.forEach((id, user1) -> {
            if (email == user1.getEmail()) {
                user.set(user1);
            }
        });

        return Optional.of(user.get());
    }

    public User save(User user) {
        String id = UUID.randomUUID().toString();

        users.put(id, user);
        return find(id).get();
    }

    public void remove(User user) {
        users.forEach((id, user1) -> {
            if (user == user1) {
                users.remove(id);
            }
        });
    }

}
