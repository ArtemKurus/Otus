package org.otus.akurus.service;

import org.springframework.shell.Availability;

public interface UserService {
    void createUser();
    void createUser(String name, String surname);

    Availability isUser();
}
