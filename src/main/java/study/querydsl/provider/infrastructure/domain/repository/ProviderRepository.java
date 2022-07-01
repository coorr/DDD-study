package study.querydsl.provider.infrastructure.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.provider.infrastructure.domain.entity.Provider;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

public interface ProviderRepository extends JpaRepository<Provider, ProviderId> {

//    findByLevelIsAndParentId(Integer level, ProviderId providerId);
}
