package team08.issuetracker.detail.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team08.issuetracker.detail.dto.IssueDetailDto;
import team08.issuetracker.detail.service.DetailService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
public class DetailController {
    private final DetailService detailService;

    // GET 요청이 /1/detail
    @GetMapping("{id}/detail")
    ResponseEntity<IssueDetailDto> getIssueDetail(@PathVariable long id) {

        IssueDetailDto response = detailService.getIssueDetailResponse(id);

        return ResponseEntity.ok(response);
    }


}
