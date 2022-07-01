package study.querydsl.provider.application.query;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import study.querydsl.config.MemberQueryDslRepositorySupport;
import study.querydsl.provider.application.query.field.ProviderDepthsQueryField;
import study.querydsl.provider.application.query.result.ProviderDepthsQueryResult;
import study.querydsl.provider.application.query.result.ProviderQueryResult;
import study.querydsl.provider.infrastructure.domain.entity.Provider;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

import java.util.List;
import java.util.Objects;

import static study.querydsl.provider.infrastructure.domain.entity.QProvider.provider;

@Service
public class ProviderSupport extends MemberQueryDslRepositorySupport<Provider> {

    public ProviderSupport() {
        super(Provider.class);
    }

    public void getList() {

    }
    public List<ProviderDepthsQueryResult> findDepthsBy(@NonNull ProviderDepthsQueryField request) {
        JPAQuery<ProviderDepthsQueryResult> query = getQueryFactory()
                .select(
                        Projections.constructor(
                                ProviderDepthsQueryResult.class,
                                provider.id.value,
                                provider.code.value,
                                provider.parent.id.value.as("parentId"),
                                provider.level,
                                provider.name,
                                provider.status,
                                provider.children.size().as("childCount")))
                .from(provider)
                .where(provider.level.eq(request.getLevel()));
//                .orderBy(this.getOrderSpecOfProviderNameContainNumber(Order.ASC));

        if (!CollectionUtils.isEmpty(request.getParentIds())) {
            query.where(provider.parent.id.in(request.getParentIds()));
        }

        // TODO 하위 호환을 위해 남겨두며 추후 제거 필요함.
        if (Objects.nonNull(request.getParentId())) {
            query.where(provider.parent.id.eq(request.getParentId()));
        }

        if (!CollectionUtils.isEmpty(request.getStatuses())) {
            query.where(provider.status.in(request.getStatuses()));
        }

        return query.fetch();
    }

    private OrderSpecifier<?> getOrderSpecOfProviderNameContainNumber(Order order) {
        return new OrderSpecifier(
                order,
                Expressions.stringTemplate(
                        "cast(substring({0}, '^[0-9]+') as int), SUBSTRING ({1}, '[^0-9].*$')",
                        provider.name, provider.name));
    }

    public ProviderQueryResult findById(ProviderId id) {
        return getQueryFactory()
                .select(
                        Projections.fields(
                                ProviderQueryResult.class,
                                provider.id.value.as("id"),
                                provider.parent.id.value.as("parentId"),
                                provider.name,
                                provider.fullCode,
                                provider.code.value.as("code"),
                                provider.fullName,
                                provider.level,
                                provider.status
                        )
                )
                .from(provider)
                .where(provider.id.eq(id))
                .fetchFirst();



    }
}
