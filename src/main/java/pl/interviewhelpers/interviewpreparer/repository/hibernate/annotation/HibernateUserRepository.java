package pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier
@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface HibernateUserRepository {
}
