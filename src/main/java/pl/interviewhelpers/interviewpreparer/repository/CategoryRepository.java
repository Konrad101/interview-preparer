package pl.interviewhelpers.interviewpreparer.repository;

import pl.interviewhelpers.interviewpreparer.repository.entity.Category;

public interface CategoryRepository {
    Category getCategory(String programmingLanguage, String categoryName);
}
