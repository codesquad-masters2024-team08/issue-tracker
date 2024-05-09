package team08.issuetracker.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import team08.issuetracker.exception.user.InvalidRegisterFormException;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(InvalidRegisterFormException.class)
    public ResponseEntity<String> handleInvalidRegisterFormException(InvalidRegisterFormException e) {
        log.error(e.getClass().getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 폼이 유효하지 않습니다.");
    }


}
