package github.aaa4.server.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class PersistenceUnit<T extends PersistenceUnit> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private UUID id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @EqualsAndHashCode.Exclude
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @EqualsAndHashCode.Exclude
    private Date updatedAt;
}
