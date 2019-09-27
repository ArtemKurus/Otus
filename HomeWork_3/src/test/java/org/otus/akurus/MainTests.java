package org.otus.akurus;


import org.junit.jupiter.api.Test;
import org.otus.akurus.dao.QuestionDaoCSV;
import org.otus.akurus.doamin.Question;
import org.otus.akurus.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;




@SpringBootTest
@EnableAutoConfiguration
public class MainTests {

    @Autowired
    private QuestionDaoCSV questionDaoCSV;

    @Autowired
    private TestService testService;

    @Test
    public void CSVTest() {
        List<Question> questions = questionDaoCSV.loadQuestionList();
        assertEquals(5, questions.size());
    }

    @Test
    public void questionTest() {
        List<Question> questions = questionDaoCSV.loadQuestionList();
        assertEquals(5, questions.size());
    }

}
