package com.api.estramipyme.respositories;

import com.api.estramipyme.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}