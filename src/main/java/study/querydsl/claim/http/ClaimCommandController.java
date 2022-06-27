package study.querydsl.claim.http;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.querydsl.claim.application.entity.command.CommandExecutor;
import study.querydsl.claim.application.entity.command.CreateClaimCommand;
import study.querydsl.claim.application.entity.command.CreateClaimModel;
import study.querydsl.claim.http.request.ClaimCreateRequest;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/claims")
@RequiredArgsConstructor
public class ClaimCommandController {
    private final CreateClaimCommand createClaimCommand;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ClaimCreateRequest request) {
        var executor =
                new CommandExecutor<>(
                        createClaimCommand,
                        CreateClaimModel.builder()
                                .studentId(request.getStudentId())
                                .targetId(request.getTargetId())
                                .type(request.getType())
                                .build()
                );
        executor.invoke();


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

