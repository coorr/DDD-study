package study.querydsl.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

@Transactional(readOnly = true)
public class MemberQueryDslRepositorySupport<T extends AbstractAggregateRoot<?>> extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public MemberQueryDslRepositorySupport(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    @PersistenceContext
    public void setEntityManager(@NotNull EntityManager entityManager) {
        super.setEntityManager(entityManager);
        this.queryFactory = new JPAQueryFactory(getEntityManager());
    }
    protected JPAQueryFactory getQueryFactory() {
        return queryFactory;
    }
}
