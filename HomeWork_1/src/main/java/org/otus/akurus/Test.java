package org.otus.akurus;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Setter
@Getter
public class Test {
    private List<Question> questionList;

    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        User user = new User(name, surname);
        System.out.println("Тестирование");
        questionList.forEach(question->{
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
