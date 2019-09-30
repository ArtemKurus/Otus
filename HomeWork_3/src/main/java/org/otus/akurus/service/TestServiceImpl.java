package org.otus.akurus.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.otus.akurus.dao.QuestionDao;
import org.otus.akurus.doamin.UserDto;
import org.otus.akurus.utils.InjectionTest;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@Service
@InjectionTest
public class TestServiceImpl implements TestService {

    private QuestionDao loaderDao;
    private MessageSource messageSource;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        loaderDao.loadQuestionList().forEach(question -> {
            System.out.print(question.getQuestion() + ": ");
            String answer = scanner.nextLine();
            System.out.print(messageSource.getMessage("test.result", null, Locale.ENGLISH));
            if (question.validate(answer))
                System.out.println(messageSource.getMessage("test.correct", null, Locale.ENGLISH));
            else
                System.out.println(messageSource.getMessage("test.wrong", null, Locale.ENGLISH));

        });
    }
}
