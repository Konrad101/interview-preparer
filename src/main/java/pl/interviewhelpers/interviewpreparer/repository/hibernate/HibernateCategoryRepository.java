package pl.interviewhelpers.interviewpreparer.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.interviewhelpers.interviewpreparer.repository.CategoryRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.Category;

public class HibernateCategoryRepository implements CategoryRepository {
    @Override
    public Category getCategory(String programmingLanguage, String categoryName) {
        String hqlQuery = "from Category " +
                "where programmingLanguage = :programmingLang and " +
                "categoryName = :category";
        Category searchedCategory = getCategoryFromQuery(
                hqlQuery,
                programmingLanguage,
                categoryName);

        if (searchedCategory == null) {
            searchedCategory = getOtherCategory();
        }

        return searchedCategory;
    }

    private Category getOtherCategory() {
        String hqlQuery = "from Category " +
                "where categoryName = :category";
        return getCategoryFromQuery(hqlQuery, null, "Other");
    }

    private Category getCategoryFromQuery(String searchHqlQuery,
                                          String programmingLanguage,
                                          String categoryName) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        final Query<Category> query = session.createQuery(searchHqlQuery, Category.class);
        if (programmingLanguage != null) {
            query.setParameter("programmingLang", programmingLanguage);
        }
        query.setParameter("category", categoryName);
        Category searchedCategory = query.uniqueResult();

        session.close();
        return searchedCategory;
    }
}
