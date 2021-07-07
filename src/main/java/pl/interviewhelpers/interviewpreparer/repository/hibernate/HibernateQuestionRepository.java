package pl.interviewhelpers.interviewpreparer.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.interviewhelpers.interviewpreparer.repository.QuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.Question;

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
    public List<Question> getUserQuestions(String username) {
        if (username == null) {
            return null;
        }

        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        final Query<Question> userQuestionsQuery = session.createQuery(
                "from Question where questionOwner.username = :uname",
                Question.class);
        userQuestionsQuery.setParameter("uname", username);
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
    public boolean editQuestion(int questionId, Question question) {
        if (!validateQuestion(question)) {
            return false;
        }
        question.setQuestionId(questionId);
        executeQuestionOperation(question, DatabaseOperation.UPDATE);
        return true;
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        final Question question = getQuestionById(questionId);
        if (!validateQuestion(question)) {
            return false;
        }
        executeQuestionOperation(question, DatabaseOperation.DELETE);
        return true;
    }

    private Question getQuestionById(int questionId) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        final Query<Question> query = session.createQuery("from Question where questionId = :id", Question.class);
        query.setParameter("id", questionId);
        final Question questionById = query.uniqueResult();
        session.close();
        return questionById;
    }

    private void executeQuestionOperation(Question question, DatabaseOperation operationName) {
        if (question == null && operationName == null)
            return;

        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch (operationName) {
            case SAVE:
                session.save(question);
                break;
            case UPDATE:
                session.update(question);
                break;
            case DELETE:
                session.delete(question);
                break;
        }
        session.getTransaction().commit();
        session.close();
    }

    private boolean validateQuestion(Question question) {
        if (question == null) {
            return false;
        }

        final String content = question.getContent();
        final String answer = question.getAnswer();
        if (content == null ||
                answer == null ||
                question.getQuestionOwner() == null ||
                question.getCategory() == null
        ) {
            return false;
        } else if (content.length() > 511 || answer.length() > 1023) {
            return false;
        } else if (content.replaceAll("\\s", "").length() == 0 ||
                answer.replaceAll("\\s", "").length() == 0) {
            return false;
        }

        return true;
    }
}
