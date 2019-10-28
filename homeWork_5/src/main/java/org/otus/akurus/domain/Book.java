package org.otus.akurus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Book {

    private long id;
    private final String title;
    private final long genre;
    private final long author;
}
