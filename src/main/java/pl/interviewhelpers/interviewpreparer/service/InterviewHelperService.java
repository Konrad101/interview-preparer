package pl.interviewhelpers.interviewpreparer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.interviewhelpers.interviewpreparer.repository.CategoryRepository;
import pl.interviewhelpers.interviewpreparer.repository.QuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.UserRepository;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateCategoryRepository;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateQuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateUserRepository;

@Service
public class InterviewHelperService {
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Autowired
    public InterviewHelperService(
            @HibernateCategoryRepository CategoryRepository categoryRepository,
            @HibernateQuestionRepository QuestionRepository questionRepository,
            @HibernateUserRepository UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }


}
