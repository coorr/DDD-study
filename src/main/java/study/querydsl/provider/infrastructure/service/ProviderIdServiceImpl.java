package study.querydsl.provider.infrastructure.service;

import org.springframework.stereotype.Service;
import study.querydsl.provider.infrastructure.domain.service.ProviderIdService;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

import java.util.UUID;

@Service
public class ProviderIdServiceImpl implements ProviderIdService {
    @Override
    public ProviderId nextProviderId() {
        return new ProviderId(UUID.randomUUID());
    }
}
