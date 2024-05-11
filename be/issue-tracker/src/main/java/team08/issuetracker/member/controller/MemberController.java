package team08.issuetracker.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team08.issuetracker.member.model.dto.MemberCreationDto;
import team08.issuetracker.member.service.MemberService;

@RestController
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> registerMember(@RequestBody MemberCreationDto memberCreationDto) {
        memberService.registerMember(memberCreationDto);

        return ResponseEntity.ok("회원가입 성공!");
    }
}
