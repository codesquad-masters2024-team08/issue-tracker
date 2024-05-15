package team08.issuetracker.milestone.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team08.issuetracker.milestone.model.Milestone;
import team08.issuetracker.milestone.model.dto.MilestoneCountDto;
import team08.issuetracker.milestone.model.dto.MilestoneCreationDto;
import team08.issuetracker.milestone.model.dto.MilestoneUpdateDto;
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

        log.debug("Milestone이 생성되었습니다. ID : {}, Name : {}", milestone.getId(), milestone.getName());

        return ResponseEntity.ok("마일스톤 생성 성공! 마일스톤 #" + milestone.getId() + " 이름 : " + milestone.getName());
    }

    @GetMapping("/count")
    public ResponseEntity<MilestoneCountDto> getMilestoneCount() {
        MilestoneCountDto milestoneCountDto = milestoneService.getMilestoneCount();

        return ResponseEntity.ok(milestoneCountDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateMilestone(@PathVariable long id, @RequestBody MilestoneUpdateDto milestoneUpdateDto) {
        Milestone milestone = milestoneService.updateMilestone(id, milestoneUpdateDto);

        return ResponseEntity.ok("마일스톤 수정 성공! 마일스톤 #" + milestone.getId() + " 이름 : " + milestone.getName());
    }
}
