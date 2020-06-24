package github.aaa4.server.accountAndRoleTests;

import github.aaa4.server.entity.Account;
import github.aaa4.server.entity.Role;
import github.aaa4.server.entity.Roles;
import github.aaa4.server.repo.AccountRepository;
import github.aaa4.server.repo.RoleRepository;
import github.aaa4.server.services.AccountAndRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
@Slf4j
public class RoleTests {

    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public AccountAndRoleService accountAndRoleService;


    Role user = new Role(Roles.USER);
    Role guest = new Role(Roles.GUEST);


    @Test
    public void testEquality(){
        assertEquals(user, new Role(Roles.USER));
        Account account = new Account();
        account.setName("Account");
        account.addNewRole(user);
        assertEquals(user, new Role(Roles.USER));
    }



    @Test
    public void testSaveNewRole(){
        accountAndRoleService.deleteAllAccounts();
        accountAndRoleService.deleteAllRoles();
       assertEquals(0, roleRepository.count());
    }

    @Test
    public void saveRole(){
        accountAndRoleService.deleteAllRoles();
        accountAndRoleService.saveRole(user);
        accountAndRoleService.saveRole(user);
        List<Role> roles = roleRepository.findAllByRole(user.getRole());
        assertEquals(1, roles.size());
        Role userFromDb = roleRepository.findDistinctByRole(user.getRole()).get();
        log.info("user: {}", user);
        log.info("userFromDb: {}", userFromDb);
        assertEquals(user, userFromDb);

    }

}
