package team08.issuetracker.milestone.model.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MilestoneSummaryDto {
    private final String name;
    private final double progress;
}