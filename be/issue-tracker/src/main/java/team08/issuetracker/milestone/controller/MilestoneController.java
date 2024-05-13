package team08.issuetracker.milestone.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team08.issuetracker.milestone.model.Milestone;
import team08.issuetracker.milestone.model.dto.MilestoneCountDto;
import team08.issuetracker.milestone.model.dto.MilestoneCreationDto;
import team08.issuetracker.milestone.service.MilestoneService;

@RestController
@Slf4j
@RequestMapping("/milestone")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @PostMapping()
    public ResponseEntity<String> saveMilestone(@RequestBody MilestoneCreationDto milestoneCreationDto) {
        Milestone milestone = milestoneService.saveMilestone(milestoneCreationDto);

        return ResponseEntity.ok("마일스톤 생성 성공! 마일스톤 #" + milestone.getId() + " 이름 : " + milestone.getName());
    }

    @GetMapping("/count")
    public ResponseEntity<MilestoneCountDto> getMilestoneCount() {
        MilestoneCountDto milestoneCountDto = milestoneService.getMilestoneCount();

        return ResponseEntity.ok(milestoneCountDto);
    }


}
