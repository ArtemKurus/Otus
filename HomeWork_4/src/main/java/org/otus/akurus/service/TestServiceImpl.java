package org.otus.akurus.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.otus.akurus.dao.QuestionDao;
import org.otus.akurus.doamin.Question;
import org.otus.akurus.utils.InjectionTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@Service
@InjectionTest
public class TestServiceImpl implements TestService {

    private QuestionDao loaderDao;
    private MessageSource messageSource;

    public int run(Scanner scanner) {
        int result = 0;
        for (Question question : loaderDao.loadQuestionList()) {
            System.out.print(question.getQuestion() + ": ");
            String answer = scanner.nextLine();
            System.out.print(messageSource.getMessage("test.result", null, LocaleContextHolder.getLocale()));
            if (question.validate(answer)) {
                System.out.println(messageSource.getMessage("test.correct", null, LocaleContextHolder.getLocale()));
                result++;
            } else
                System.out.println(messageSource.getMessage("test.wrong", null, LocaleContextHolder.getLocale()));

        }
        return result;
    }
}
