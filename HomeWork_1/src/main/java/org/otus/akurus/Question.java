package org.otus.akurus;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
public class Question implements Serializable {
    private int id;
    private String question;
    private String answer;

    public boolean validate(String answer){
        return this.answer.trim().toLowerCase().equals(answer.trim().toLowerCase());
    }
}
