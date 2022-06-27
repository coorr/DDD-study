package study.querydsl.member.presentation.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.querydsl.member.application.entity.Member;
import study.querydsl.member.application.entity.query.MemberQuery;
import study.querydsl.member.application.entity.query.field.MemberTeamQueryField;

import java.util.List;

@RestController
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberQueryController {

    private final MemberQuery memberQuery;

    @GetMapping("/{memberId}/{teamId}")
    public ResponseEntity<Object> getMemberTeamBy(@PathVariable Long memberId, @PathVariable Long teamId) {
        List<Member> result = memberQuery.getMemberTeamBy(
                MemberTeamQueryField.builder()
                        .memberId(memberId)
                        .teamId(teamId)
                        .build()
        );
        return ResponseEntity.ok().body(result);
    }
}
