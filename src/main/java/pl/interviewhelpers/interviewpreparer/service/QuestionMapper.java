package pl.interviewhelpers.interviewpreparer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.interviewhelpers.interviewpreparer.controller.dto.QuestionRequest;
import pl.interviewhelpers.interviewpreparer.controller.dto.QuestionResponse;
import pl.interviewhelpers.interviewpreparer.repository.CategoryRepository;
import pl.interviewhelpers.interviewpreparer.repository.UserRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.Category;
import pl.interviewhelpers.interviewpreparer.repository.entity.Question;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;

@Component
@RequiredArgsConstructor
public class QuestionMapper {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public QuestionResponse map(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }

        final Category category = question.getCategory();
        String categoryStr = category.getProgrammingLanguage() == null ?
                category.getCategoryName() :
                category.getProgrammingLanguage() + ", " + category.getCategoryName();
        return QuestionResponse
                .builder()
                .questionId(question.getQuestionId())
                .content(question.getContent())
                .answer(question.getAnswer())
                .category(categoryStr)
                .ownerUsername(question.getQuestionOwner().getUsername())
                .build();
    }

    public Question map(QuestionRequest questionRequest) {
        if (questionRequest == null) {
            throw new IllegalArgumentException("Question request cannot be null");
        }

        final String username = questionRequest.getOwnerUsername();
        final User user = userRepository.getUserByUsername(username);
        final Category questionCategory = categoryRepository.getCategory(
                questionRequest.getProgrammingLanguage(),
                questionRequest.getCategoryName());
        return Question
                .builder()
                .content(questionRequest.getContent())
                .answer(questionRequest.getAnswer())
                .questionOwner(user)
                .category(questionCategory)
                .build();
    }
}
