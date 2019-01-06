package de.hszg.webservices.ts05.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Email implements Serializable, Comparable<Email> {

    private final String value;

    public static Email of(String value) {
        return new Email(value);
    }


    @Override
    public int compareTo(Email other) {
        if (other == null) {
            return -1;
        }
        return this.value.compareTo(other.value);
    }
}
