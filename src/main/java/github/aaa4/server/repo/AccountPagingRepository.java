package github.aaa4.server.repo;

import github.aaa4.server.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountPagingRepository extends JpaRepository<Account, UUID> {
    Page<Account> findAll(Pageable pageable);
}
