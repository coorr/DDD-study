package study.querydsl.member.application.entity.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.querydsl.member.application.entity.Member;
import study.querydsl.member.application.entity.query.field.MemberTeamQueryField;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberQuery {
    private final MemberSupport memberSupport;

    public List<Member> getMemberTeamBy(MemberTeamQueryField field) {
        return memberSupport.findMemberTeamBy(field);
    }
}
