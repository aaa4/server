package github.aaa4.server.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Role extends PersistenceUnit {

    @NonNull
    private Roles role;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Account> accounts = new HashSet<>();


    public void addNewAccount(Account account){
        if (accounts == null){
            accounts = new HashSet<>();
        }
        accounts.add(account);
        account.getRoles().add(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        if (!super.equals(o)) return false;
        Role role1 = (Role) o;
        return role == role1.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role);
    }


    @Override
    public String toString() {
        return "Role{" +
                "role=" + role +
                ", accounts=" + accounts +
                '}';
    }
}
