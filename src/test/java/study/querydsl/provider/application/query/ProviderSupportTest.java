package study.querydsl.provider.application.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.member.presentation.http.ProviderStatus;
import study.querydsl.provider.infrastructure.domain.entity.Provider;
import study.querydsl.provider.infrastructure.domain.repository.ProviderRepository;
import study.querydsl.provider.infrastructure.domain.vo.ProviderCode;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

import static study.querydsl.provider.infrastructure.domain.entity.QProvider.provider;

@DisplayName("출처관리 QueryDsl Support")
@Transactional
@SpringBootTest
class ProviderSupportTest {

    @PersistenceContext
    EntityManager em;

    @Autowired private  ProviderSupport support;

    @Autowired ProviderQuery query;

    @Autowired ProviderRepository repository;

    private JPAQueryFactory queryFactory;

    private final ProviderId parentId = new ProviderId(UUID.randomUUID());

    private final ProviderId childId = new ProviderId(UUID.randomUUID());


    @BeforeEach
    void setup() {
        queryFactory = new JPAQueryFactory(em);

        Provider provider =
                Provider.builder()
                        .id(parentId)
                        .level(0)
                        .name("test")
                        .fullCode("0000")
                        .fullName("test")
                        .code(new ProviderCode("0000"))
                        .status(ProviderStatus.USED)
                        .build();

        Provider parent = repository.save(provider);

        Provider child =
                Provider.builder()
                        .id(childId)
                        .level(1)
                        .parent(parent)
                        .name("교과서")
                        .fullCode("0001")
                        .fullName("test1")
                        .code(new ProviderCode("0001"))
                        .status(ProviderStatus.USED)
                        .build();

        repository.save(child);
    }


    @DisplayName("Depths 조회 : parentId")
    @Test
    void findDepthsBy() {
        JPAQuery<Provider> query = queryFactory
                .selectFrom(provider)
                .where(provider.level.eq(1).and(provider.parent.id.eq(parentId)));

        List<Provider> fetch = query.fetch();

        Assertions.assertThat(fetch).isNotEmpty();
    }


}