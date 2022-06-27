package study.querydsl.claim.application.model;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.claim.application.entity.Claim;
import study.querydsl.claim.application.entity.ClaimId;

public interface ClaimRepository extends JpaRepository<Claim, ClaimId> {
}
