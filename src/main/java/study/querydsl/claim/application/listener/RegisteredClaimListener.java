package study.querydsl.claim.application.listener;

import lombok.Getter;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import study.querydsl.claim.application.domain.event.RegisteredClaim;

@Getter
@Component
public class RegisteredClaimListener {

    @EventListener
    public void handle(RegisteredClaim event) {
        System.out.println("232323232323232323232");
    }
}
