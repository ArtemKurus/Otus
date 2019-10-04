package org.otus.akurus.service;

import org.otus.akurus.doamin.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Scanner;

@Service
public class UserServiceImpl implements UserService {


    private final MessageSource messageSource;

    @Autowired
    public UserServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(messageSource.getMessage("test.user", null, null));
        String name = scanner.nextLine();
        System.out.println(messageSource.getMessage("test.surname", null, LocaleContextHolder.getLocale()));
        String surname = scanner.nextLine();
        new UserDto(name, surname);
    }
}
