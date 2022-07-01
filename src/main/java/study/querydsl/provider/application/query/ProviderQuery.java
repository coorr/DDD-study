package study.querydsl.provider.application.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.querydsl.provider.application.query.field.ProviderDepthsQueryField;
import study.querydsl.provider.application.query.result.ProviderDepthsQueryResult;
import study.querydsl.provider.application.query.result.ProviderQueryResult;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderQuery {
    private final ProviderSupport support;


    public List<ProviderDepthsQueryResult> getDepthsBy(ProviderDepthsQueryField request) {
        return support.findDepthsBy(request);
    }

    public ProviderQueryResult getProvider(ProviderId id) {
        return support.findById(id);
    }
}
