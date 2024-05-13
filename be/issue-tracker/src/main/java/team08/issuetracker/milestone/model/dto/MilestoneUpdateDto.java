package team08.issuetracker.milestone.model.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MilestoneUpdateDto {
    private String name;
    private String description; // null 가능
    private LocalDate completeDate; // null 가능

    public MilestoneUpdateDto(String name, String description, LocalDate completeDate) {
        this.name = name;
        this.description = description;
        this.completeDate = completeDate;
    }
}
