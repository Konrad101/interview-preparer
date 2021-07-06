package pl.interviewhelpers.interviewpreparer.repository;

import pl.interviewhelpers.interviewpreparer.repository.entity.Question;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;

import java.util.List;

public interface QuestionRepository {
    List<Question> getAllQuestions();
    List<Question> getUserQuestions(User user);
    boolean addQuestion(Question question);
    boolean editQuestion(Question question);
    boolean deleteQuestion(Question question);
}
