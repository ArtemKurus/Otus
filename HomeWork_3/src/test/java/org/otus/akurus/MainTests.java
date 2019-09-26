package org.otus.akurus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.otus.akurus.dao.QuestionDaoCSV;
import org.otus.akurus.doamin.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class MainTests {

    @Autowired
    private QuestionDaoCSV questionDaoCSV;

    @Test
    public void CSVTest() {
        List<Question> questions = questionDaoCSV.loadQuestionList();
        assertEquals(5, questions.size());
    }

}
