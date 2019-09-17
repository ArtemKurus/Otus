package org.otus.akurus.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.otus.akurus.dao.QuestionDao;
import org.otus.akurus.doamin.User;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final QuestionDao loaderDao;

    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        User user = new User(name, surname);
        System.out.println("Тестирование");
        loaderDao.loadQuestionList().forEach(question->{
            System.out.print(question.getQuestion()+": ");
            String answer = scanner.nextLine();
            System.out.print("result: ");
            if (question.validate(answer))
                System.out.println("ок");
            else
                System.out.println("wrong");

        });
        System.out.println("Конец");
    }
}
