package team08.issuetracker.milestone.model.dto;

import lombok.Getter;

@Getter
public class MilestoneCountDto {
    private long totalCount;
    private long openedCount;
    private long closedCount;

    public MilestoneCountDto(long totalCount, long openedCount, long closedCount) {
        this.totalCount = totalCount;
        this.openedCount = openedCount;
        this.closedCount = closedCount;
    }
}
