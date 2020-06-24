package github.aaa4.server.services;

import github.aaa4.server.entity.Account;
import github.aaa4.server.entity.Role;
import github.aaa4.server.entity.Roles;
import github.aaa4.server.entity.VerificationToken;
import github.aaa4.server.repo.AccountRepository;
import github.aaa4.server.repo.RoleRepository;
import github.aaa4.server.repo.VerificationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AccountAndRoleService {


    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private VerificationTokenRepository verificationTokenRepository;

    public AccountAndRoleService(AccountRepository accountRepository, RoleRepository roleRepository, VerificationTokenRepository verificationTokenRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public Optional<Account> findDistinctById(UUID id){
        return accountRepository.findById(id);
    }


    //delete block

    public void deleteAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(acc -> {
            acc.setRoles(null);
            acc.setTokens(null);
            accountRepository.save(acc);
        });
        accountRepository.deleteAll();

    }

    public void deleteAllRoles(){
        List<Role> roles = roleRepository.findAll();
        for (Role role : roles){
            role.setAccounts(null);
            roleRepository.save(role);
        }
        roleRepository.deleteAll();
    }

    public void deleteAllTokens() {

        List<VerificationToken> tokens = verificationTokenRepository.findAll();
        for (VerificationToken token: tokens){
            token.setAccount(new Account());
            verificationTokenRepository.save(token);
        }
        verificationTokenRepository.deleteAll();
    }

    public void deleteAccount(Account account){
        account.setRoles(new HashSet<>());
        accountRepository.save(account);
        accountRepository.delete(account);
    }


    //saving block
    public void saveRole(Role role){
        if (roleRepository.findDistinctByRole(role.getRole()).isPresent()){
            log.info("Role "+role+" already existed in database.");
        }else{
            log.info("Saving new "+role+" to database.");
            roleRepository.save(role);
        }
    }

    public void saveAllAccounts(List<Account> accounts){
        accountRepository.saveAll(accounts);
    }


    @Transactional
    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }


    public void populateWithRoles(){
        Role guest = new Role(Roles.GUEST);
        Role user = new Role(Roles.USER);
        Role editor = new Role(Roles.EDITOR);
        Role admin = new Role(Roles.ADMIN);
        Role root = new Role(Roles.SUPERADMIN);




    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }


}
