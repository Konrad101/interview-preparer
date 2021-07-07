package pl.interviewhelpers.interviewpreparer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.interviewhelpers.interviewpreparer.repository.entity.Category;
import pl.interviewhelpers.interviewpreparer.repository.entity.Question;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.HibernateQuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.HibernateUserRepository;

@SpringBootApplication
public class InterviewPreparerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewPreparerApplication.class, args);
    }

}
