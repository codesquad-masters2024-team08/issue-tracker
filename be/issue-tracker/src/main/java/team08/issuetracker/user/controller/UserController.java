package team08.issuetracker.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team08.issuetracker.user.model.dto.UserCreationDto;

@RestController
@Slf4j
public class UserController {

    public ResponseEntity<String> registerUser(@RequestBody UserCreationDto userCreationDto) {

    }
}
