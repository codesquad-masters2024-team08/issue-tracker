package team08.issuetracker.member.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("MEMBER")
public class Member {
    @Id
    @Column("member_id")
    private String memberId;

    @Column("password")
    private String password;

    public Member(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}