package org.otus.akurus.dao;

import org.otus.akurus.doamin.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> loadQuestionList();
}
