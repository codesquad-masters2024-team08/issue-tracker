package team08.issuetracker.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import team08.issuetracker.jwt.JwtService;
import team08.issuetracker.member.model.Member;
import team08.issuetracker.member.model.dto.MemberCreationDto;
import team08.issuetracker.member.model.dto.MemberLoginDto;
import team08.issuetracker.member.service.CookieService;
import team08.issuetracker.member.service.MemberService;

@RestController
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin(exposedHeaders = {"Authorization", "Set-Cookie"})
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;
    private final CookieService cookieService;

    private static final String TOKEN_NAME = "jwt-token";
    private static final String TOKEN_HEADER_VALUE = "Bearer ";

    @PostMapping
    public ResponseEntity<String> registerMember(@RequestBody MemberCreationDto memberCreationDto) {
        memberService.registerMember(memberCreationDto);

        return ResponseEntity.ok("회원가입 성공!");
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody MemberLoginDto memberLoginDto) {
        Member member = memberService.loginMember(memberLoginDto);

        String token = jwtService.createJwtToken(member);

        HttpCookie cookie = cookieService.createCookie(token);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, TOKEN_HEADER_VALUE + token);
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok()
                .headers(headers)
                .body("로그인 성공!");
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@CookieValue(name = TOKEN_NAME) String jwtToken) {
        if (!jwtService.parseJwtToken(jwtToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 토큰");
        }

        return ResponseEntity.ok("검증 성공");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutMember() {

        ResponseCookie responseCookie = ResponseCookie.from(TOKEN_NAME, "")
                .maxAge(0)
                .path("/")
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .build();
    }

}
