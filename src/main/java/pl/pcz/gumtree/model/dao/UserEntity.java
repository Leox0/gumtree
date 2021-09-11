package pl.pcz.gumtree.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data //@Getter @Setter
@NoArgsConstructor //for POJO
@AllArgsConstructor //for builder
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(unique = true, name = "mail")
    private String mail;

    @Column(unique = true, name = "nick")
    private String nick;

    @Column(name = "password")
    private String password;

}
