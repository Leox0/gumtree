package pl.pcz.gumtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pcz.gumtree.model.dao.AuthorityEntity;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

        Optional<AuthorityEntity> findByName(String name);

    }

