package github.aaa4.server.repo;

import github.aaa4.server.entity.Role;
import github.aaa4.server.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository  extends JpaRepository<Role, UUID> {

    Optional<Role> findDistinctByRole(Roles role);
    List<Role> findAllByRole(Roles role);
}
