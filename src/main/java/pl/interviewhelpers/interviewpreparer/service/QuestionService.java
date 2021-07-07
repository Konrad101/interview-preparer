package pl.interviewhelpers.interviewpreparer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.interviewhelpers.interviewpreparer.controller.dto.QuestionRequest;
import pl.interviewhelpers.interviewpreparer.controller.dto.QuestionResponse;
import pl.interviewhelpers.interviewpreparer.repository.CategoryRepository;
import pl.interviewhelpers.interviewpreparer.repository.QuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.UserRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.Question;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateCategoryRepository;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateQuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(
            @HibernateCategoryRepository CategoryRepository categoryRepository,
            @HibernateQuestionRepository QuestionRepository questionRepository,
            @HibernateUserRepository UserRepository userRepository,
            QuestionMapper questionMapper) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.questionMapper = questionMapper;
    }

    public List<QuestionResponse> getUserQuestionsResponses(String username) {
        final List<Question> userQuestions = questionRepository.getUserQuestions(username);
        return mapQuestionsToResponses(userQuestions);
    }

    public List<QuestionResponse> getAllQuestionsResponses() {
        final List<Question> questions = questionRepository.getAllQuestions();
        return mapQuestionsToResponses(questions);
    }

    public boolean createQuestion(QuestionRequest questionRequest) {
        final Question question = questionMapper.map(questionRequest);
        return questionRepository.addQuestion(question);
    }

    public boolean updateQuestion(int questionId, QuestionRequest questionRequest) {
        final Question question = questionMapper.map(questionRequest);
        return questionRepository.editQuestion(questionId, question);
    }

    public boolean deleteQuestion(int questionId) {
        return questionRepository.deleteQuestion(questionId);
    }

    private List<QuestionResponse> mapQuestionsToResponses(List<Question> questions) {
        return questions
                .stream()
                .map(questionMapper::map)
                .collect(Collectors.toList());
    }
}
