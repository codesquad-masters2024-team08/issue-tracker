package team08.issuetracker.milestone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team08.issuetracker.exception.milestone.InvalidMilestoneFormException;
import team08.issuetracker.milestone.model.Milestone;
import team08.issuetracker.milestone.model.dto.MilestoneCountDto;
import team08.issuetracker.milestone.model.dto.MilestoneCreationDto;
import team08.issuetracker.milestone.repository.MilestoneRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;

    public Milestone saveMilestone(MilestoneCreationDto milestoneCreationDto) {
        validateMilestoneForm(milestoneCreationDto);

        Milestone milestone = new Milestone(milestoneCreationDto.getName(), milestoneCreationDto.getDescription(), milestoneCreationDto.getCompleteDate());

        log.debug("Milestone이 생성되었습니다. Name : {}", milestone.getName());

        return milestoneRepository.save(milestone);
    }

    public MilestoneCountDto getMilestoneCount() {
        long totalCount = milestoneRepository.count();
        long openedCount = milestoneRepository.countOpenedMilestones();
        long closedCount = milestoneRepository.countClosedMilestones();

        return new MilestoneCountDto(totalCount, openedCount, closedCount);
    }

    private void validateMilestoneForm(MilestoneCreationDto milestoneCreationDto) {
        if (milestoneCreationDto.getName() == null || milestoneCreationDto.getName().isEmpty()) {
            throw new InvalidMilestoneFormException();
        }
    }


}
