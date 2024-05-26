package team08.issuetracker.issue.model;


import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import team08.issuetracker.issue.model.dto.update.IssueAssigneeUpdateRequest;
import team08.issuetracker.issue.model.dto.update.IssueContentUpdateRequest;
import team08.issuetracker.issue.model.dto.update.IssueLabelUpdateRequest;
import team08.issuetracker.issue.model.dto.update.IssueMilestoneUpdateRequest;
import team08.issuetracker.issue.model.dto.update.IssueTitleUpdateRequest;
import team08.issuetracker.issue.ref.Assignee;
import team08.issuetracker.issue.ref.IssueAttachedLabel;

@ToString
@Setter
@Getter
public class Issue {
    @Id
    private Long id;
    private boolean isOpen; // default : open 수정 할 때 만 클라이언트에서 값 받는다.
    private final String writer; //Non-Null
    private String title; //Non-Null
    private String content;
    private final String uploadedFile;
    private Long milestoneId;
    private LocalDateTime createdAt;

    @MappedCollection(idColumn = "ISSUE_ID")
    private Set<Assignee> assignees;

    @MappedCollection(idColumn = "ISSUE_ID")
    private Set<IssueAttachedLabel> issueAttachedLabels;

    public Issue(String title, String writer, String content, String uploadedFile, Long milestoneId) {

        // issue 생성시 서버에서 초기화 값을 부여하는 필드들
        this.isOpen = true; // 이슈 생성시 true를 기본값으로 초기화 되어야 한다
        this.createdAt = LocalDateTime.now(); // 이슈 생성시, 생성 시간을 저장

        // issue 생성시 클라이언트에서 받은 값으로 초기화 하는 필드들
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.uploadedFile = uploadedFile;
        this.milestoneId = milestoneId;
    }

    // 다대다 관계는 setter를 통해 저장
    public void setAssignees(Set<Assignee> assignees) {
        this.assignees = assignees;
    }

    public void setIssueAttachedLabels(Set<IssueAttachedLabel> issueAttachedLabels) {
        this.issueAttachedLabels = issueAttachedLabels;
    }

    public void updateTitle(IssueTitleUpdateRequest issueTitleUpdateRequest) {
        this.title = issueTitleUpdateRequest.title();
    }

    public void updateContent(IssueContentUpdateRequest issueContentUpdateRequest) {
        this.content = issueContentUpdateRequest.content();
    }

    public void updateAssignee(IssueAssigneeUpdateRequest issueAssigneeUpdateRequest) {
        this.assignees = issueAssigneeUpdateRequest.getAssigneesOfIssue(this.id);
    }

    public void updateIssueAttachedLabel(IssueLabelUpdateRequest issueLabelUpdateRequest) {
        this.issueAttachedLabels = issueLabelUpdateRequest.getIssueAttachedLabel(this.id);
    }

    public void updateMilestone(IssueMilestoneUpdateRequest issueMilestoneUpdateRequest) {
        this.milestoneId = issueMilestoneUpdateRequest.milestoneId();
    }

    public void open() {
        this.isOpen = true;
    }

    public void close() {
        this.isOpen = false;
    }
}
