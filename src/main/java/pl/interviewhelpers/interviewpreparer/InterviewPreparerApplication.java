package pl.interviewhelpers.interviewpreparer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.interviewhelpers.interviewpreparer.repository.UserRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.HibernateUserRepository;

@SpringBootApplication
public class InterviewPreparerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(InterviewPreparerApplication.class, args);
        UserRepository userRepository = new HibernateUserRepository();
        User user = new User();
        user.setUsername("hlib101");
        user.setEmail("m@o2.pl");
        user.setFirstName("Hleb");
        user.setLastName("Lib");
		System.out.println(userRepository.addUser(user));
    }

}
