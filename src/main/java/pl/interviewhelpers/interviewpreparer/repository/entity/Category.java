package pl.interviewhelpers.interviewpreparer.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIES", schema = "QUESTIONS")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CATEGORY_ID")
    private int categoryId;
    @Column(name = "PROGRAMMING_LANGUAGE")
    private String programmingLanguage;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
}
