package org.otus.akurus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Author {
    private long id;
    private String name;
    private String countryName;
    private LocalDate dateOfBirth;
}
