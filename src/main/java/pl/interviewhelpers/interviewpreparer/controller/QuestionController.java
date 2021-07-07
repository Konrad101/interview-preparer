package pl.interviewhelpers.interviewpreparer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.interviewhelpers.interviewpreparer.controller.dto.QuestionRequest;
import pl.interviewhelpers.interviewpreparer.controller.dto.QuestionResponse;
import pl.interviewhelpers.interviewpreparer.service.QuestionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping(path = "/interview-api/question")
    public ResponseEntity<List<QuestionResponse>> getQuestions(@RequestParam(required = false) String username) {
        if (username == null) {
            return new ResponseEntity<>(questionService.getAllQuestionsResponses(), HttpStatus.OK);
        }

        return new ResponseEntity<>(questionService.getUserQuestionsResponses(username), HttpStatus.OK);
    }

    @PostMapping(path = "/interview-api/question")
    public ResponseEntity createQuestion(@RequestBody QuestionRequest request) {
        boolean success = questionService.createQuestion(request);
        if (success) {
            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/interview-api/question")
    public ResponseEntity updateQuestion(
            @RequestBody QuestionRequest request,
            @RequestParam int id) {
        boolean success = questionService.updateQuestion(id, request);
        if (success) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/interview-api/question")
    public ResponseEntity<QuestionResponse> deleteQuestion(
            @RequestParam int id
    ) {
        boolean success = questionService.deleteQuestion(id);
        if (success) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
