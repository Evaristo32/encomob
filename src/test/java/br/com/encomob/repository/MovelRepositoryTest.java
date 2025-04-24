package br.com.encomob.repository;

import br.com.encomob.domain.Movel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // força usar H2
@DataJpaTest
class MovelRepositoryTest {

    @Autowired
    private MovelRepository movelRepository;


    @Test
    void testFindById() {
        // Arrange
        Movel movel = new Movel();
        movel.setNome("Cadeira Gamer");
        movel.setDescricao("Cadeira confortável para escritório");
        Movel saved = movelRepository.save(movel);

        // Act
        Optional<Movel> found = movelRepository.findById(saved.getId());

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getNome()).isEqualTo("Cadeira Gamer");
    }

}
