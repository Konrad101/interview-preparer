package pl.interviewhelpers.interviewpreparer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserPatchOperation;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserPatchRequest;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserRequest;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserResponse;
import pl.interviewhelpers.interviewpreparer.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/interview-api/user")
    public ResponseEntity<UserResponse> getUserByUsername(@RequestParam String username) {
        final UserResponse userByUsername = userService.getUserByUsername(username);
        HttpStatus status = userByUsername == null ?
                HttpStatus.NOT_FOUND :
                HttpStatus.OK;
        return new ResponseEntity<>(userByUsername, status);
    }

    @PostMapping(path = "/interview-api/user")
    public ResponseEntity createUser(@RequestBody UserRequest request) {
        boolean success = userService.createUser(request);
        if (success) {
            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "interview-api/user")
    public ResponseEntity updateUser(@RequestBody UserPatchRequest patchRequest) {
        if (patchRequest.isOperationAvailable() && patchRequest.isPathValid()) {
            boolean success = false;
            if (patchRequest.getPatchOperation() == UserPatchOperation.CHANGE_PHONE_NUMBER) {
                success = userService.updatePhoneNumber(
                        patchRequest.getUsername(),
                        patchRequest.getValue());
            } else if (patchRequest.getPatchOperation() == UserPatchOperation.CHANGE_EMAIL) {
                success = userService.updateEmail(
                        patchRequest.getUsername(),
                        patchRequest.getValue());
            }
            return new ResponseEntity(success ?
                    HttpStatus.ACCEPTED :
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
