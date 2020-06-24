package github.aaa4.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Slf4j
@NoArgsConstructor
public class VerificationToken extends PersistenceUnit {

    @Transient
    private final int EXPIRATION = 60*10;
    private String token;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Date expiryDate;

    public VerificationToken(String token, Account account) {
        this.token = token;
        this.account = account;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int timeInMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, timeInMinutes);
        return new Date(calendar.getTime().getTime());

    }




}
