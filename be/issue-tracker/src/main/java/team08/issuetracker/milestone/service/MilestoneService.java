package team08.issuetracker.milestone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import team08.issuetracker.exception.milestone.InvalidMilestoneFormException;
import team08.issuetracker.exception.milestone.MilestoneAlreadyClosedException;
import team08.issuetracker.exception.milestone.MilestoneAlreadyOpenedException;
import team08.issuetracker.exception.milestone.MilestoneNotFoundException;
import team08.issuetracker.milestone.model.Milestone;
import team08.issuetracker.milestone.model.dto.MilestoneCountDto;
import team08.issuetracker.milestone.model.dto.MilestoneCreationDto;
import team08.issuetracker.milestone.model.dto.MilestoneUpdateDto;
import team08.issuetracker.milestone.repository.MilestoneRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;

    private final boolean OPEN = true;
    private final boolean CLOSE = false;

    public Milestone saveMilestone(MilestoneCreationDto milestoneCreationDto) {
        validateMilestoneForm(milestoneCreationDto.name());

        Milestone milestone = new Milestone(milestoneCreationDto.name(), milestoneCreationDto.description(), milestoneCreationDto.completeDate());

        return milestoneRepository.save(milestone);
    }

    public MilestoneCountDto getMilestoneCount() {
        long totalCount = milestoneRepository.count();
        long openedCount = milestoneRepository.countOpenedMilestones();
        long closedCount = milestoneRepository.countClosedMilestones();

        return new MilestoneCountDto(totalCount, openedCount, closedCount);
    }

    public Milestone updateMilestone(Long id, MilestoneUpdateDto milestoneUpdateDto) {
        validateMilestoneForm(milestoneUpdateDto.name());

        Milestone milestone = milestoneRepository.findById(id).orElseThrow(MilestoneNotFoundException::new);

        milestone.update(milestoneUpdateDto);

        return milestoneRepository.save(milestone);
    }

    public Milestone openMilestone(Long id) {
        Milestone milestone = milestoneRepository.findById(id).orElseThrow(MilestoneNotFoundException::new);

        if (milestone.isOpen()) {
            throw new MilestoneAlreadyOpenedException();
        }

        milestone.updateOpenState(OPEN);

        return milestoneRepository.save(milestone);
    }

    public Milestone closeMilestone(Long id) {
        Milestone milestone = milestoneRepository.findById(id).orElseThrow(MilestoneNotFoundException::new);

        if (!milestone.isOpen()) {
            throw new MilestoneAlreadyClosedException();
        }

        milestone.updateOpenState(CLOSE);

        return milestoneRepository.save(milestone);
    }


    private void validateMilestoneForm(String milestoneName) {
        if (milestoneName == null || milestoneName.isEmpty()) {
            throw new InvalidMilestoneFormException();
        }
    }


}
