package team08.issuetracker.user.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class UserCreationDto {
    private String userId;
    private String password;
}
