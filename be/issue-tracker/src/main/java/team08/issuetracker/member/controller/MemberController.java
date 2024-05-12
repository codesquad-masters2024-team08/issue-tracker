package team08.issuetracker.member.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import team08.issuetracker.jwt.JwtService;
import team08.issuetracker.member.model.Member;
import team08.issuetracker.member.model.MemberResponse;
import team08.issuetracker.member.model.dto.MemberCreationDto;
import team08.issuetracker.member.model.dto.MemberLoginDto;
import team08.issuetracker.member.service.MemberService;

@RestController
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<String> registerMember(@RequestBody MemberCreationDto memberCreationDto) {
        memberService.registerMember(memberCreationDto);

        return ResponseEntity.ok("회원가입 성공!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody MemberLoginDto memberLoginDto) {
        Member member = memberService.loginMember(memberLoginDto);

        MemberResponse response = new MemberResponse(member.getMemberId(), jwtService.createJwtToken(member));

        HttpCookie httpCookie = ResponseCookie.from("jwt-token", response.getToken())
                .maxAge(7776000)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, httpCookie.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + response.getToken())
                .build();
    }

    @GetMapping("/validate")
    public ResponseEntity<?> my(@CookieValue(name = "jwt-token") String requestRefreshToken){
        jwtService.parseJwtToken(requestRefreshToken);

        return ResponseEntity.ok("");
    }

}
