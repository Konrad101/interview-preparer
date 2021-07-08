# interview-preparer
Restful CRUD API for people that want to share common interview questions.

## About the project

### Built With

* Java SDK 11.0.2 

* Spring Boot 2.5.2

* Hibernate

* PostgreSQL

### About app URL

By default the app will start at <http://localhost:8080>

### Explore REST APIs

#### Users

| Method | URL | Decription | Sample Response | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------- | ------------------------- |
| GET   | /interview-api/user?username={username} | Getting data about user | [JSON](#get_user) |  |
| POST   | /interview-api/user | Add new user |  | [JSON](#post_user) |
| PATCH   | /interview-api/user?username={username} | Allows to change user email or phone number |  | email: [JSON](#patch_email) <br /> phone number: [JSON](#patch_phone_number) |

#### Questions

| Method | URL | Decription | Sample Response | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------- | ------------------------- |
| GET   | /interview-api/question | Getting all questions stored in database | [JSON](#get_all_questions) |  |
| GET   | /interview-api/question?username={username} | Getting all user questions | [JSON](#get_user_questions) |  |
| POST   | /interview-api/question | Add new question |  | [JSON](#post_question) |
| PUT   | /interview-api/question?id={id} | Edit existing question |  | [JSON](#put_question) |
| DELETE   | /interview-api/question?id={id} | Delete question |  |  |

### Sample JSON responses

#### Users

##### <a id="get_user">Get user data: /interview-api/user?username=us3rn4m3</a>
```json
{
    "username": "us3rn4m3",
    "email": "us3rn4m3_mail@aa.com",
    "fullName": "Name Lastname"
}
```

#### Questions

##### <a id="get_all_questions">Get all questions: /interview-api/question</a>
```json
[
    {
        "questionId": 1,
        "content": "What is JPA?",
        "answer": "Specification that describes the management of relational data in enterprise Java applications",
        "ownerUsername": "manno11",
        "category": "Java, Databases"
    },
    {
        "questionId": 2,
        "content": "What is ORM?",
        "answer": "Technique that lets you manipulate data from a database using an object-oriented paradigm",
        "ownerUsername": "us3rn4m3",
        "category": "Java, Databases"
    },
    {
        "questionId": 3,
        "content": "What is JDBC?",
        "answer": "Java API that manages connecting to a database, issuing queries and commands, and handling result sets obtained from the database",
        "ownerUsername": "us3rn4m3",
        "category": "Java, Databases"
    }
]
```


##### <a id="get_user_questions">Get user questions: /interview-api/question?username=us3rn4m3</a>
```json
[
    {
        "questionId": 2,
        "content": "What is ORM?",
        "answer": "Technique that lets you manipulate data from a database using an object-oriented paradigm",
        "ownerUsername": "us3rn4m3",
        "category": "Java, Databases"
    },
    {
        "questionId": 3,
        "content": "What is JDBC?",
        "answer": "Java API that manages connecting to a database, issuing queries and commands, and handling result sets obtained from the database",
        "ownerUsername": "us3rn4m3",
        "category": "Java, Databases"
    }
]
```


### Sample valid JSON request body

#### Users

##### <a id="post_user">Add new user: /interview-api/user</a>
```json
{
    "username": "us3rn4m3",
    "email": "us3rn4m3_mail@aa.com",
    "firstName": "Name",
    "lastName": "Lastname"
}
```


##### <a id="patch_email">Update user email: /interview-api/user?username=us3rn4m3</a>
```json
{
    "op": "replace",
    "path": "/email",
    "value": "some_email@aa.com"
}
```


##### <a id="patch_phone_number">Update user phone number: /interview-api/user?username=us3rn4m3</a>
```json
{
    "op": "replace",
    "path": "/phoneNumber",
    "value": "123-456-7890"
}
```

#### Questions

##### <a id="post_question">Add new question: /interview-api/question</a>
```json
{
    "content": "What is JDBC?",
    "answer": "Java API that manages connecting to a database, issuing queries and commands, and handling result sets obtained from the database",
    "ownerUsername": "us3rn4m3",
    "programmingLanguage": "Java",
    "categoryName": "Databases"
}
```

##### <a id="put_question">Edit question: /interview-api/question?id=3</a>
```json
{
    "content": "What is JDBC?",
    "answer": "Java Database Connectivity, java API that manages connecting to a database, issuing queries and commands, and handling result sets obtained from the database",
    "ownerUsername": "us3rn4m3",
    "programmingLanguage": "Java",
    "categoryName": "Databases"
}
```

