package github.aaa4.server.entity;

import java.util.UUID;

public class MainForData {

    public static void main(String[] args) {

        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setName("Rus");

        Role guest = new Role(Roles.GUEST);

        account.addNewRole(guest);
        System.out.println(account);


    }
}
