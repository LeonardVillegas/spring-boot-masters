package com.api.estramipyme.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.estramipyme.user.User;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
