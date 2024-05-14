package team08.issuetracker.label.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class LabelCreationDto {
    private String name ;
    private String description;
    private String backgroundColor;
    private String textColor;
}
