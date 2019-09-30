package org.otus.akurus.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.otus.akurus.dao.QuestionDao;
import org.otus.akurus.doamin.UserDto;
import org.otus.akurus.utils.InjectionTest;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@Service
@InjectionTest
public class TestServiceImpl implements TestService {

    private QuestionDao loaderDao;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        loaderDao.loadQuestionList().forEach(question -> {
            System.out.print(question.getQuestion() + ": ");
            String answer = scanner.nextLine();
            System.out.print("result: ");
            if (question.validate(answer))
                System.out.println("ок");
            else
                System.out.println("wrong");

        });
    }
}
