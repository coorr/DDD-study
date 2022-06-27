package study.querydsl.claim.application.entity.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.claim.application.entity.Claim;
import study.querydsl.claim.application.entity.StudentId;
import study.querydsl.claim.application.entity.TargetId;
import study.querydsl.claim.application.model.ClaimRepository;

@Service
@RequiredArgsConstructor
public class CreateClaimCommand implements Command<CreateClaimModel>{
    private final ClaimRepository claimRepository;

    @Transactional
    @Override
    public void execute(CreateClaimModel createClaimModel) {
        claimRepository.save(
                Claim.create(
                        StudentId.of(createClaimModel.getStudentId()),
                        TargetId.of(createClaimModel.getTargetId()),
                        createClaimModel.getType()
                )
        );
    }
}
