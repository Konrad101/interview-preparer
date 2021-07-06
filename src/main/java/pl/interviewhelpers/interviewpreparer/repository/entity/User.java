package pl.interviewhelpers.interviewpreparer.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USERS", schema = "USERS")
@SecondaryTable(name = "PERSON", schema = "USERS")
@Data
public class User {
    @Id
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FIRST_NAME", table = "PERSON")
    private String firstName;
    @Column(name = "LAST_NAME", table = "PERSON")
    private String lastName;
    @Column(name = "PHONE_NUMBER", table = "PERSON")
    private String phoneNumber;

}
