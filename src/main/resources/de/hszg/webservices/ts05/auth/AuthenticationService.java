package de.hszg.webservices.ts05.auth;

import de.hszg.webservices.ts05.user.Email;
import de.hszg.webservices.ts05.user.User;
import de.hszg.webservices.ts05.user.UserRepository;
import io.jsonwebtoken.Jwts;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Dependent
public class AuthenticationService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private JwtKeyPairStore keyStore;

    public Optional<String> loginAndGetToken(Email email, String password, String issuer) {
        if (this.validateCredentials(email, password)) {
            return Optional.of(this.issueToken(email, issuer));
        }
        return Optional.empty();
    }
    private boolean validateCredentials(Email email, String password) {
        Optional<User> user = this.userRepository.find(email);
        return user.isPresent() && Objects.equals(user.get().getPassword(), password);
    }
    private String issueToken(Email email, String issuer) {
        return Jwts.builder()
                .setSubject(email.getValue())
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(null)
                .signWith(this.keyStore.getPrivateKey())
                .compact();
    }
}