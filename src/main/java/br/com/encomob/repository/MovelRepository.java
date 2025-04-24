package br.com.encomob.repository;
import br.com.encomob.domain.Movel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovelRepository extends JpaRepository<Movel, UUID> {
}
