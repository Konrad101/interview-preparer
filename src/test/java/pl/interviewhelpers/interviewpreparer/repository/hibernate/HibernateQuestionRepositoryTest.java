package pl.interviewhelpers.interviewpreparer.repository.hibernate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.interviewhelpers.interviewpreparer.repository.QuestionRepository;
import pl.interviewhelpers.interviewpreparer.repository.UserRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.Category;
import pl.interviewhelpers.interviewpreparer.repository.entity.Question;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;
import pl.interviewhelpers.interviewpreparer.repository.exception.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HibernateQuestionRepositoryTest {
    private QuestionRepository questionRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        questionRepository = new HibernateQuestionRepository(userRepository);
    }

    @Test
    void getUserQuestions_nullUsername_notNullQuestionResponse() {
        // given
        final String username = null;
        // when/then
        assertThrows(
                IllegalArgumentException.class,
                () -> questionRepository.getUserQuestions(username)
        );
    }

    @Test
    void getUserQuestions_emptyUsername_notNullQuestionResponse() {
        // given
        final String username = "";
        // when/then
        assertThrows(
                IllegalArgumentException.class,
                () -> questionRepository.getUserQuestions(username)
        );
    }

    @Test
    void getUserQuestions_notExistingUsername_thrownUserNotFoundException() {
        // given
        final String username = "not_existing_username";
        when(userRepository.getUserByUsername(username))
                .thenReturn(null);
        // when/then
        assertThrows(
                UserNotFoundException.class,
                () -> questionRepository.getUserQuestions(username)
        );
    }

    @Test
    void addQuestion_nullContent_false() {
        // given
        final Question question = new Question();
        question.setContent(null);
        question.setAnswer("answer");
        User user = User
                .builder()
                .username("username")
                .email("email@a.com")
                .firstName("first_name")
                .lastName("last_name")
                .build();
        question.setQuestionOwner(user);
        Category category = new Category();
        category.setCategoryName("category");
        question.setCategory(category);
        // when
        boolean success = questionRepository.addQuestion(question);
        // then
        assertFalse(success);
    }

    @Test
    void addQuestion_nullAnswer_false() {
        // given
        final Question question = new Question();
        question.setContent("content");
        question.setAnswer(null);
        User user = User
                .builder()
                .username("username")
                .email("email@a.com")
                .firstName("first_name")
                .lastName("last_name")
                .build();
        question.setQuestionOwner(user);
        Category category = new Category();
        category.setCategoryName("category");
        question.setCategory(category);
        // when
        boolean success = questionRepository.addQuestion(question);
        // then
        assertFalse(success);
    }

    @Test
    void addQuestion_nullUser_false() {
        // given
        final Question question = new Question();
        question.setAnswer("answer");
        question.setContent("content");
        question.setQuestionOwner(null);
        Category category = new Category();
        category.setCategoryName("category");
        question.setCategory(category);
        // when
        boolean success = questionRepository.addQuestion(question);
        // then
        assertFalse(success);
    }

    @Test
    void addQuestion_nullCategory_false() {
        // given
        final Question question = new Question();
        question.setContent("content");
        question.setAnswer("answer");
        User user = User
                .builder()
                .username("username")
                .email("email@a.com")
                .firstName("first_name")
                .lastName("last_name")
                .build();
        question.setQuestionOwner(user);
        question.setCategory(null);
        // when
        boolean success = questionRepository.addQuestion(question);
        // then
        assertFalse(success);
    }

    @Test
    void editQuestion_nullContent_false() {
        // given
        final Question question = new Question();
        question.setContent(null);
        question.setAnswer("answer");
        User user = User
                .builder()
                .username("username")
                .email("email@a.com")
                .firstName("first_name")
                .lastName("last_name")
                .build();
        question.setQuestionOwner(user);
        Category category = new Category();
        category.setCategoryName("category");
        question.setCategory(category);
        // when
        boolean success = questionRepository.editQuestion(1, question);
        // then
        assertFalse(success);
    }

    @Test
    void editQuestion_nullAnswer_false() {
        // given
        final Question question = new Question();
        question.setContent("content");
        question.setAnswer(null);
        User user = User
                .builder()
                .username("username")
                .email("email@a.com")
                .firstName("first_name")
                .lastName("last_name")
                .build();
        question.setQuestionOwner(user);
        Category category = new Category();
        category.setCategoryName("category");
        question.setCategory(category);
        // when
        boolean success = questionRepository.editQuestion(1, question);
        // then
        assertFalse(success);
    }

    @Test
    void editQuestion_nullUser_false() {
        // given
        final Question question = new Question();
        question.setContent("content");
        question.setAnswer("answer");
        question.setQuestionOwner(null);
        Category category = new Category();
        category.setCategoryName("category");
        question.setCategory(category);
        // when
        boolean success = questionRepository.editQuestion(1, question);
        // then
        assertFalse(success);
    }

    @Test
    void editQuestion_nullCategory_false() {
        // given
        final Question question = new Question();
        question.setContent("content");
        question.setAnswer("answer");
        User user = User
                .builder()
                .username("username")
                .email("email@a.com")
                .firstName("first_name")
                .lastName("last_name")
                .build();
        question.setQuestionOwner(user);
        question.setCategory(null);
        // when
        boolean success = questionRepository.editQuestion(1, question);
        // then
        assertFalse(success);
    }

    @Test
    void editQuestion_negativeId_thrownIllegalArgumentException() {
        // given
        final int questionId = -1;
        final Question question = new Question();
        question.setContent("content");
        question.setAnswer("answer");
        User user = User
                .builder()
                .username("username")
                .email("email@a.com")
                .firstName("first_name")
                .lastName("last_name")
                .build();
        question.setQuestionOwner(user);
        question.setCategory(null);
        // when/then
        assertThrows(
                IllegalArgumentException.class,
                () -> questionRepository.editQuestion(questionId, question)
        );
    }

    @Test
    void deleteQuestion_negativeId_thrownIllegalArgumentException() {
        // given
        final int questionId = -1;
        // when/then
        assertThrows(
                IllegalArgumentException.class,
                () -> questionRepository.deleteQuestion(questionId)
        );
    }
}