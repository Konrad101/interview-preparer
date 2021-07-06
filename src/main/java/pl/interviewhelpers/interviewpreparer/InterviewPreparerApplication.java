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
//        SpringApplication.run(InterviewPreparerApplication.class, args);
        Question question = new Question();
        question.setQuestionId(1);
        question.setContent("Czym jest JPA?");
        question.setAnswer("Jest to specyfikacja mówiąca o tym w jaki sposób implementować połączenie z bazą.");
        User user = new User();
        user.setUsername("manno11");
        question.setQuestionOwner(user);
        Category category = new Category();
        category.setProgrammingLanguage("Java");
        category.setCategoryName("Hibernate");
        question.setCategory(category);
        HibernateQuestionRepository repository = new HibernateQuestionRepository();
        repository.addQuestion(question);
    }

}
