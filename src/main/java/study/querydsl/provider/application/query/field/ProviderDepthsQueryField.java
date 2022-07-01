package study.querydsl.provider.application.query.field;

import lombok.*;
import study.querydsl.member.presentation.http.ProviderStatus;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProviderDepthsQueryField {
    @Builder.Default private int level = 0;

    private ProviderId parentId;

    @Builder.Default private List<ProviderId> parentIds = new ArrayList<>();

    @Builder.Default private List<ProviderStatus> statuses = List.of(ProviderStatus.USED);

}
