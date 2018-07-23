package co.napsak.api.repository;

import co.napsak.api.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<UserDetails> findByUsername(String username);
}
