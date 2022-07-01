package study.querydsl.member.application.entity.query;

import org.springframework.stereotype.Service;
import study.querydsl.config.MemberQueryDslRepositorySupport;
import study.querydsl.member.application.entity.Member;
import study.querydsl.member.application.entity.QMember;
import study.querydsl.member.application.entity.QTeam;
import study.querydsl.member.application.entity.query.field.MemberTeamQueryField;

import java.util.List;

@Service
public class MemberSupport extends MemberQueryDslRepositorySupport<Member> {
    public MemberSupport() {
        super(Member.class);
    }

    QMember m = new QMember("m");
    QTeam t = new QTeam("t");


    public List<Member> findMemberTeamBy(MemberTeamQueryField field) {
        List<Member> result = this.getQueryFactory()
                .selectFrom(m)
                .join(m.team, t)
                .fetch();
        return result;

    }
}
