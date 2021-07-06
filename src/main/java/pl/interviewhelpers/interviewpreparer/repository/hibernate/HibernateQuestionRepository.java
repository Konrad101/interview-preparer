package pl.interviewhelpers.interviewpreparer.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.interviewhelpers.interviewpreparer.repository.CategoryRepository;
import pl.interviewhelpers.interviewpreparer.repository.QuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.Category;
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
        if (question == null && operationName == null)
            return;

        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch (operationName) {
            case SAVE:
                setQuestionCategory(question);
                session.save(question);
                break;
            case UPDATE:
                setQuestionCategory(question);
                session.update(question);
                break;
            case DELETE:
                session.delete(question);
                break;
        }
        session.getTransaction().commit();
        session.close();
    }

    private void setQuestionCategory(Question question) {
        if (question == null)
            return;

        final Category category = question.getCategory();
        CategoryRepository categoryRepository = new HibernateCategoryRepository();
        final Category categoryFromDatabase = categoryRepository.getCategory(
                category.getProgrammingLanguage(),
                category.getCategoryName());
        question.setCategory(categoryFromDatabase);
    }

    private boolean validateQuestion(Question question) {
        if (question == null) {
            return false;
        }

        final String content = question.getContent();
        final String answer = question.getAnswer();
        if (content == null || answer == null) {
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
