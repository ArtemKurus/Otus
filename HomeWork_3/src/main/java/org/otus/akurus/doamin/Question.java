package org.otus.akurus.doamin;

import lombok.Getter;

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
