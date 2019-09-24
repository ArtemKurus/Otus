package org.otus.akurus.dao;

import lombok.AllArgsConstructor;
import org.otus.akurus.doamin.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository("questionDao")
public class QuestionDaoCSV extends CSVLoadre implements QuestionDao {

    private final DaoConfig daoConfig;

    @Override
    public List<Question> loadQuestionList() {
        return loadObjectList(daoConfig.getFileName(), Question.class);
    }
}
