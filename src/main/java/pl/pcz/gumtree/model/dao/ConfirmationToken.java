package pl.pcz.gumtree.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "confirmationToken")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;

    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createData;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private UserEntity userEntity;

    public ConfirmationToken(UserEntity userEntity) {
        this.userEntity = userEntity;
        createData = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }


}
