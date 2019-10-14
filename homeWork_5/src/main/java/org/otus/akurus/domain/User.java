package org.otus.akurus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String surname;
}