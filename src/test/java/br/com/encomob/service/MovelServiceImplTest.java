package br.com.encomob.service;

import br.com.encomob.domain.Movel;
import br.com.encomob.repository.MovelRepository;
import br.com.encomob.service.impl.MovelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovelServiceImplTest {

    @Mock
    private MovelRepository movelRepository;

    @InjectMocks
    private MovelServiceImpl movelService;

    private Movel movel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movel = new Movel();
        movel.setId(UUID.randomUUID());
        movel.setNome("Mesa");
        movel.setPreco(BigDecimal.valueOf(100.0));
    }

    @Test
    void testSave() {
        when(movelRepository.save(any(Movel.class))).thenReturn(movel);

        Movel savedMovel = movelService.save(movel);

        assertNotNull(savedMovel);
        assertEquals("Mesa", savedMovel.getNome());
        verify(movelRepository, times(1)).save(movel);
    }

    @Test
    void testFindById() {
        when(movelRepository.findById(any())).thenReturn(Optional.of(movel));

        Optional<Movel> foundMovel = movelService.findById(movel.getId());

        assertTrue(foundMovel.isPresent());
        assertEquals("Mesa", foundMovel.get().getNome());
        verify(movelRepository, times(1)).findById(movel.getId());
    }

    @Test
    void testFindAll() {
        when(movelRepository.findAll()).thenReturn(List.of(movel));

        List<Movel> moveis = movelService.findAll();

        assertFalse(moveis.isEmpty());
        assertEquals(1, moveis.size());
        verify(movelRepository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(movelRepository.existsById(any())).thenReturn(true);
        when(movelRepository.save(any(Movel.class))).thenReturn(movel);

        Movel updatedMovel = movelService.update(movel);

        assertNotNull(updatedMovel);
        assertEquals("Mesa", updatedMovel.getNome());
        verify(movelRepository, times(1)).existsById(movel.getId());
        verify(movelRepository, times(1)).save(movel);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(movelRepository.existsById(any())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> movelService.update(movel));

        assertEquals("Móvel com ID " + movel.getId() + " não encontrado.", exception.getMessage());
        verify(movelRepository, times(1)).existsById(movel.getId());
        verify(movelRepository, never()).save(any(Movel.class));
    }

    @Test
    void testDeleteById() {
        when(movelRepository.existsById(any())).thenReturn(true);
        doNothing().when(movelRepository).deleteById(any());

        assertDoesNotThrow(() -> movelService.deleteById(movel.getId()));

        verify(movelRepository, times(1)).existsById(movel.getId());
        verify(movelRepository, times(1)).deleteById(movel.getId());
    }


    @Test
    void testDeleteByIdThrowsExceptionWhenNotFound() {
        when(movelRepository.existsById(any())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> movelService.deleteById(movel.getId()));

        assertEquals("Móvel com ID " + movel.getId() + " não encontrado.", exception.getMessage());
        verify(movelRepository, times(1)).existsById(movel.getId());
        verify(movelRepository, never()).deleteById(any());
    }
}
