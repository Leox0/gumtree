package pl.pcz.gumtree.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pcz.gumtree.model.dao.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
