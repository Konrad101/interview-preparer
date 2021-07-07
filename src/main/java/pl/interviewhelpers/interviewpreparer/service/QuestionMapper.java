package pl.interviewhelpers.interviewpreparer.service;

import pl.interviewhelpers.interviewpreparer.controller.dto.QuestionResponse;
import pl.interviewhelpers.interviewpreparer.repository.entity.Category;
import pl.interviewhelpers.interviewpreparer.repository.entity.Question;

public class QuestionMapper {
    public QuestionResponse map(Question question) {
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
}
