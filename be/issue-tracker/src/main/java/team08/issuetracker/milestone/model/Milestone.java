package team08.issuetracker.milestone.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;

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

}
