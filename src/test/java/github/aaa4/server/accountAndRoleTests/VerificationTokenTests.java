package github.aaa4.server.accountAndRoleTests;

import github.aaa4.server.entity.Account;
import github.aaa4.server.entity.VerificationToken;
import github.aaa4.server.repo.AccountRepository;
import github.aaa4.server.repo.VerificationTokenRepository;
import github.aaa4.server.services.AccountAndRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VerificationTokenTests {


    @Autowired
    AccountRepository accountRepository;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    @Autowired
    AccountAndRoleService accountAndRoleService;

    @Test
    public void testNotEquality() {

        VerificationToken t1 = new VerificationToken();
        t1.setToken(UUID.randomUUID().toString());
        VerificationToken t2 = new VerificationToken();
        t2.setToken(UUID.randomUUID().toString());
        assertNotEquals(t1, t2);
    }


    @Test
    public void testNotEqualToTokensWithAccounts() {
        Account a1 = new Account();
        a1.setName("a1");

        Account a2 = new Account();
        a2.setName("a2");

        VerificationToken t1 = new VerificationToken();
        a1.addNewToken(t1);
        VerificationToken t2 = new VerificationToken();
        a2.addNewToken(t2);
        System.out.println(t1);
        System.out.println(t2);
        assertNotEquals(t1, t2);
    }

    @Test
    public void saveSingleTokenWithAccount(){
        accountAndRoleService.deleteAllAccounts();
        accountAndRoleService.deleteAllTokens();

        assertEquals(0, verificationTokenRepository.count());
        assertEquals(0, accountRepository.count());

        Account a1 = new Account();
        a1.setName("a1");
        accountRepository.save(a1);

        VerificationToken t1 = new VerificationToken("tokenOne",a1);

        verificationTokenRepository.save(t1);

        System.out.println("t1:"+t1);

       /* a1.addNewToken(t1);
        accountAndRoleService.saveAccount(a1);*/

        Account savedAccount = accountRepository.findById(a1.getId()).get();
        Set<VerificationToken> tokens = savedAccount.getTokens();

        assertEquals(true, tokens.contains(t1));
        assertEquals(1, tokens.size());
      /*  for (VerificationToken t: tokens){
            assertEquals(a1, t.getAccount());
        }*/





    }

}
