package de.hszg.webservices.ts05.auth;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
@Slf4j
@Getter
public class JwtKeyPairStore implements Serializable {
    private static final int RSA_KEY_LENGTH = 2048;
    private Key publicKey;
    private Key privateKey;

    @PostConstruct
    public void initializeKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(RSA_KEY_LENGTH);
            KeyPair keypair = keyPairGenerator.genKeyPair();
            this.publicKey = keypair.getPublic();
            this.privateKey = keypair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            log.error("Error creating keypair.", e);
        }
    }
}