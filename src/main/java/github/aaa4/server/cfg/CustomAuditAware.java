package github.aaa4.server.cfg;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class CustomAuditAware implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}
