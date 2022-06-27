package study.querydsl.member.application.entity.query.field;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberTeamQueryField {
    private Long memberId;
    private Long teamId;
}
