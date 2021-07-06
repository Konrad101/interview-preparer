package pl.interviewhelpers.interviewpreparer.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.interviewhelpers.interviewpreparer.repository.QuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.Question;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;

import java.util.List;

public class HibernateQuestionRepository implements QuestionRepository {
    @Override
    public List<Question> getAllQuestions() {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        final Query<Question> questionsQuery = session.createQuery("from Question", Question.class);
        final List<Question> questions = questionsQuery.list();
        session.close();
        return questions;
    }

    @Override
    public List<Question> getUserQuestions(User user) {
        if (user == null) {
            return null;
        }

        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        final Query<Question> userQuestionsQuery = session.createQuery(
                "from Question where questionOwner.username = :uname",
                Question.class);
        userQuestionsQuery.setParameter("uname", user.getUsername());
        final List<Question> userQuestions = userQuestionsQuery.list();
        session.close();
        return userQuestions;
    }

    @Override
    public boolean addQuestion(Question question) {
        if (!validateQuestion(question)) {
            return false;
        }
        executeQuestionOperation(question, DatabaseOperation.SAVE);
        return true;
    }

    @Override
    public boolean editQuestion(Question question) {
        if (!validateQuestion(question)) {
            return false;
        }
        executeQuestionOperation(question, DatabaseOperation.UPDATE);
        return true;
    }

    @Override
    public boolean deleteQuestion(Question question) {
        if (!validateQuestion(question)) {
            return false;
        }
        executeQuestionOperation(question, DatabaseOperation.DELETE);
        return true;
    }

    private void executeQuestionOperation(Question question, DatabaseOperation operationName) {
        if(question == null && operationName == null)
            return;

        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch (operationName){
            case SAVE:
                session.save(question);
            case UPDATE:
                session.update(question);
            case DELETE:
                session.delete(question);
        }
        session.getTransaction().commit();
        session.close();
    }

    private boolean validateQuestion(Question question) {

        return true;
    }
}
