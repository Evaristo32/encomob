package br.com.encomob.controller;

import br.com.encomob.domain.Movel;
import br.com.encomob.service.MovelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MovelController.class)
@ExtendWith(MockitoExtension.class)
class MovelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MovelService movelService;

    @Test
    void testCreateMovel() throws Exception {
        Movel movel = new Movel();
        movel.setId(UUID.randomUUID());
        movel.setNome("Mesa");
        movel.setPreco(BigDecimal.valueOf(100.0));

        Mockito.when(movelService.save(any(Movel.class))).thenReturn(movel);

        mockMvc.perform(post("/api/moveis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movel)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Mesa"));
    }

    @Test
    void testFindById() throws Exception {
        Movel movel = new Movel();
        movel.setId(UUID.randomUUID());
        movel.setNome("Cadeira");

        Mockito.when(movelService.findById(any(UUID.class))).thenReturn(Optional.of(movel));

        mockMvc.perform(get("/api/moveis/{id}", movel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Cadeira"));
    }

    @Test
    void testFindAll() throws Exception {
        Movel movel1 = new Movel();
        movel1.setId(UUID.randomUUID());
        movel1.setNome("Mesa");

        Movel movel2 = new Movel();
        movel2.setId(UUID.randomUUID());
        movel2.setNome("Cadeira");

        Mockito.when(movelService.findAll()).thenReturn(List.of(movel1, movel2));

        mockMvc.perform(get("/api/moveis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testUpdateMovel() throws Exception {
        Movel movel = new Movel();
        movel.setId(UUID.randomUUID());
        movel.setNome("Mesa Atualizada");

        Mockito.when(movelService.update(any(Movel.class))).thenReturn(movel);

        mockMvc.perform(put("/api/moveis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Mesa Atualizada"));
    }

    @Test
    void testDeleteById() throws Exception {
        Mockito.doNothing().when(movelService).deleteById(any(UUID.class));

        mockMvc.perform(delete("/api/moveis/{id}", UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }
}
