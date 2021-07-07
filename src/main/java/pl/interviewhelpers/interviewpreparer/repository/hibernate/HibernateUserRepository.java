package pl.interviewhelpers.interviewpreparer.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.interviewhelpers.interviewpreparer.repository.DuplicatedUsernameException;
import pl.interviewhelpers.interviewpreparer.repository.UserRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HibernateUserRepository implements UserRepository {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

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
        updateUser(user);

        return true;
    }

    @Override
    public boolean changeEmail(String username, String newEmail) {
        if (!validateEmail(newEmail)) {
            return false;
        }
        User user = getUserByUsername(username);
        user.setEmail(newEmail);
        updateUser(user);

        return true;
    }

    private void updateUser(User user) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUserByUsername(String username) {
        username = username.toLowerCase(Locale.ROOT);
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        final Query<User> query = session.createQuery("from User where username = :uname", User.class);
        // to prevent SQL injection
        query.setParameter("uname", username);
        User user = query.uniqueResult();
        session.close();
        return user;
    }

    private boolean validateUser(User user) {
        if (user == null ||
                !validatePhoneNumber(user.getPhoneNumber()) ||
                !validateEmail(user.getEmail())) {
            return false;
        } else if (user.getUsername().replaceAll("\\s", "").length() < user.getUsername().length() ||
                user.getUsername().length() == 0 ||
                user.getUsername().length() > 63) {
            return false;
        } else if (user.getFirstName().replaceAll("\\s", "").length() == 0 ||
                user.getLastName().replaceAll("\\s", "").length() == 0 ||
                user.getFirstName().length() > 63 ||
                user.getLastName().length() > 63) {
            return false;
        }

        return true;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null)
            return true;
        else if (phoneNumber.length() > 15)
            return false;
        String differentPatterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$"
                + "|^(\\d{3}[- .]?){2}\\d{3}$";
        Pattern pattern = Pattern.compile(differentPatterns);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.find();
    }

    private boolean validateEmail(String email) {
        if (email == null || email.length() > 127)
            return false;

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
