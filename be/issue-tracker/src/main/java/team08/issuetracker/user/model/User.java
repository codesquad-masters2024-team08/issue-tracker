package team08.issuetracker.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {
    @Getter
    private final String userId;
    private final String password;
}

