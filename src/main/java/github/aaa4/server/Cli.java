package github.aaa4.server;

import github.aaa4.server.entity.Account;
import github.aaa4.server.entity.Role;
import github.aaa4.server.entity.Roles;
import github.aaa4.server.services.AccountAndRoleService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Slf4j
@Component

public class Cli implements CommandLineRunner {

    private AccountAndRoleService accountAndRoleService;


    public Cli(AccountAndRoleService accountAndRoleService) {
        this.accountAndRoleService = accountAndRoleService;
    }

    @Override
    public void run(String... args) throws Exception {
       // generateAccounts();


    }

    private void generateAccounts(){
        List<Account> accounts = new ArrayList<>();
        int numberOfAccounts = 10000;
        for (int i = 0; i < numberOfAccounts; i++) {
            Account a = new Account();
            a.setName("a"+i+2);
            a.setEmail("test"+i+2+"@gmail.com");
            accounts.add(a);

        }
        accountAndRoleService.saveAllAccounts(accounts);
    }


    public static void main(String[] args) {
        //x-x*30 = 37
        //0.7x = 37
        //x = 370/7
    }
}
