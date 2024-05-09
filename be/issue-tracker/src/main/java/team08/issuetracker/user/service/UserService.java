package team08.issuetracker.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team08.issuetracker.exception.user.InvalidRegisterFormException;
import team08.issuetracker.user.model.dto.UserCreationDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserService {

    private static final String ID_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,16}$";
    private static final String PW_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,12}$";


    public void registerUser(UserCreationDto userCreationDto) {
        validateRegisterForm(userCreationDto);

    }

    private void validateRegisterForm(UserCreationDto userCreationDto) {
        String userId = userCreationDto.getUserId();
        String password = userCreationDto.getPassword();

        // 1) id, pw에 대한 공백 검증
        if (userId.isEmpty() || password.isEmpty()) {
            log.error("id, pw 중에 공백이 있습니다.");
            throw new InvalidRegisterFormException();
        }

        // 2) id의 정규식 검사 + 길이 검사
        Matcher idMatcher = Pattern.compile(ID_REGEX).matcher(userId);
        if (!idMatcher.matches()) {
            log.error("id가 형식에 맞지 않습니다.");
            throw new InvalidRegisterFormException();
        }

        // 3) pw의 정규식 검사 + 길이 검사
        Matcher passwordMatcher = Pattern.compile(PW_REGEX).matcher(password);
        if (!passwordMatcher.matches()) {
            log.error("pw가 형식에 맞지 않습니다.");
            throw new InvalidRegisterFormException();
        }
    }

}
