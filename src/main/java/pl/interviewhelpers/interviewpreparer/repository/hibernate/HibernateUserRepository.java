package pl.interviewhelpers.interviewpreparer.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.interviewhelpers.interviewpreparer.repository.DuplicatedUsernameException;
import pl.interviewhelpers.interviewpreparer.repository.UserRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;

import java.util.*;

@Component
@Qualifier("hibernateUser")
public class HibernateUserRepository implements UserRepository {

    @Override
    public boolean addUser(User newUser) {
        // change username to lowercase
        newUser.setUsername(newUser.getUsername().toLowerCase(Locale.ROOT));

        // checks if username already exists in db
        if (getUserByUsername(newUser.getUsername()) != null) {
            throw new DuplicatedUsernameException();
        }

        if (!validateUser(newUser)) {
            return false;
        }

        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(newUser);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean changePhoneNumber(String username, String newNumber) {
        if (!validatePhoneNumber(newNumber)) {
            return false;
        }
        User user = getUserByUsername(username);
        user.setPhoneNumber(newNumber);

        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean changeEmail(String username, String newEmail) {
        return true;
    }

    private User getUserByUsername(String username) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        final Query<User> query = session.createQuery("from User where username=:uname", User.class);
        query.setParameter("uname", username);
        User user = query.uniqueResult();
        session.close();
        return user;
    }

    private boolean validateUser(User user) {
        return true;
    }

    private boolean validatePhoneNumber(String email) {
        return true;
    }

    private boolean validateEmail(String email) {
        return true;
    }
}