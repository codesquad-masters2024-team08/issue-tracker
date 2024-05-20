package team08.issuetracker.member.service;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
    private static final long TOKEN_DURATION_90_DAYS = 90 * 24 * 24 * 60;
    private static final String TOKEN_NAME = "jwt-token";

    public HttpCookie createCookie(String token) {
        return ResponseCookie.from(TOKEN_NAME, token)
                .maxAge(TOKEN_DURATION_90_DAYS)
                .httpOnly(true) // http only 설정
                .secure(false)  // https 설정 X
                .path("/")  // domain 모든 경로에 대해 설정
                .build();
    }
}
