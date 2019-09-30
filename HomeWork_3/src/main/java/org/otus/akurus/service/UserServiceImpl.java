package org.otus.akurus.service;

import org.otus.akurus.doamin.UserDto;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        new UserDto(name, surname);
    }
}
