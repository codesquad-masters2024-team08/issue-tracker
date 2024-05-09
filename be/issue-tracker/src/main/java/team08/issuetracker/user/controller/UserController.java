package team08.issuetracker.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team08.issuetracker.user.model.dto.UserCreationDto;
import team08.issuetracker.user.service.UserService;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserCreationDto userCreationDto) {
        log.info("signup {}{}", userCreationDto.getUserId(), userCreationDto.getPassword());

        userService.registerUser(userCreationDto);

        return ResponseEntity.ok("회원가입 성공!");
    }
}
