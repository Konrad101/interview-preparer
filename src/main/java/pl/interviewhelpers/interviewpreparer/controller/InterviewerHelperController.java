package pl.interviewhelpers.interviewpreparer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.interviewhelpers.interviewpreparer.controller.dto.DtoResponse;
import pl.interviewhelpers.interviewpreparer.controller.dto.QuestionResponse;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;
import pl.interviewhelpers.interviewpreparer.service.InterviewHelperService;

@RestController
@RequiredArgsConstructor
public class InterviewerHelperController {
    private final InterviewHelperService helperService;

    @GetMapping(path = "/api/question")
    public ResponseEntity<QuestionResponse> getQuestions() {
        return new ResponseEntity<>(new QuestionResponse(), HttpStatus.OK);
    }

    @GetMapping(path = "/api/question/")
    public ResponseEntity<QuestionResponse> getQuestionById(@RequestParam int id) {
        return new ResponseEntity<>(new QuestionResponse(), HttpStatus.OK);
    }

    /*@PostMapping(value = "/api/user")
    // W parametrze ma byc DTO (Data Transfer Object)
    public ResponseEntity<DtoResponse> createUser(@RequestBody User user){
        return new ResponseEntity<>();
    }*/
}
