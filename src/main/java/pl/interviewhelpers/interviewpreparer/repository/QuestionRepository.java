package pl.interviewhelpers.interviewpreparer.repository;

import pl.interviewhelpers.interviewpreparer.repository.entity.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> getAllQuestions();
    List<Question> getUserQuestions(String username);
    boolean addQuestion(Question question);
    boolean editQuestion(int questionId, Question question);
    boolean deleteQuestion(int questionId);
}
