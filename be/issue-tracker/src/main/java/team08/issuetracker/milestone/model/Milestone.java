package team08.issuetracker.milestone.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import team08.issuetracker.milestone.model.dto.MilestoneUpdateDto;

import java.time.LocalDate;

@Getter
public class Milestone {
    @Id
    private Long id;
    private boolean isOpen;
    private String name;
    private String description;
    private LocalDate completeDate;

    public Milestone(String name, String description, LocalDate completeDate) {
        this.isOpen = true;
        this.name = name;
        this.description = description;
        this.completeDate = completeDate;
    }

    public Milestone update(MilestoneUpdateDto milestoneUpdateDto) {
        this.name = milestoneUpdateDto.getName();
        this.description = milestoneUpdateDto.getDescription();
        this.completeDate = milestoneUpdateDto.getCompleteDate();

        return this;
    }

}
