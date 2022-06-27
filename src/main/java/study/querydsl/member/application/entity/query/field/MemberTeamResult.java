package study.querydsl.member.application.entity.query.field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberTeamResult {
    private Long memberId;
    private Long teamId;
    private String username;
    private int age;


}
