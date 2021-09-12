package pl.pcz.gumtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pcz.gumtree.model.dao.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select user from UserEntity user " + "where user.mail = ?1 or user.nick = ?1")
    UserEntity findByMailOrNick(String s);

}
