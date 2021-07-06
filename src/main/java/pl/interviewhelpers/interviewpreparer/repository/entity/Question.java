package pl.interviewhelpers.interviewpreparer.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "QUESTIONS", schema = "QUESTIONS")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "QUESTION_ID")
    private int questionId;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "ANSWER")
    private String answer;
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User questionOwner;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
