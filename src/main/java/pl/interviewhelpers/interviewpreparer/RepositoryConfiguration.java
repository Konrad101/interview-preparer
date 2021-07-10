package pl.interviewhelpers.interviewpreparer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.HibernateCategoryRepository;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.HibernateQuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.HibernateUserRepository;

@Configuration
public class RepositoryConfiguration {

    @Bean
    @pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateCategoryRepository
    public HibernateCategoryRepository categoryRepository(){
        return new HibernateCategoryRepository();
    }

    @Bean
    @pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateQuestionRepository
    public HibernateQuestionRepository questionRepository(){
        return new HibernateQuestionRepository(userRepository());
    }

    @Bean
    @pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateUserRepository
    public HibernateUserRepository userRepository(){
        return new HibernateUserRepository();
    }
}
