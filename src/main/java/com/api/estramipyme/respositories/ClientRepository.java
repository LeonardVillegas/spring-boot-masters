package com.api.estramipyme.respositories;

import com.api.estramipyme.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
