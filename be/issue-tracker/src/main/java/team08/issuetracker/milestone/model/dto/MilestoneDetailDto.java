package team08.issuetracker.milestone.model.dto;

import lombok.Getter;
import team08.issuetracker.milestone.model.Milestone;

import java.time.LocalDate;

@Getter
public class MilestoneDetailDto {
    private long id;
    private String name;
    private LocalDate completeDate;
    private String description;
    private long openedIssueCount;
    private long closedIssueCount;
    private double milestoneProgress;

    public MilestoneDetailDto(Long id, String name, LocalDate completeDate, String description) {
        this.id = id;
        this.name = name;
        this.completeDate = completeDate;
        this.description = description;
        this.openedIssueCount = 10;
        this.closedIssueCount = 10;
        this.milestoneProgress = 50.0;
    }

    public static MilestoneDetailDto from(Milestone milestone) {
        return new MilestoneDetailDto(
                milestone.getId(),
                milestone.getName(),
                milestone.getCompleteDate(),
                milestone.getDescription()
        );
    }

}
