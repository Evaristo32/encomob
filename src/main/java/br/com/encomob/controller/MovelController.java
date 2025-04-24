package br.com.encomob.controller;

import br.com.encomob.domain.Movel;
import br.com.encomob.service.MovelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/moveis")
public class MovelController {

    private final MovelService movelService;

    public MovelController(MovelService movelService) {
        this.movelService = movelService;
    }


    @PostMapping
    public ResponseEntity<Movel> create(@RequestBody Movel movel) {
        Movel savedMovel = movelService.save(movel);
        return new ResponseEntity<>(savedMovel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movel> findById(@PathVariable UUID id) {
        Optional<Movel> movel = movelService.findById(id);
        return movel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Movel>> findAll() {
        List<Movel> moveis = movelService.findAll();
        return ResponseEntity.ok(moveis);
    }

    @PutMapping
    public ResponseEntity<Movel> update(@RequestBody Movel movel) {
        try {
            Movel updatedMovel = movelService.update( movel);
            return ResponseEntity.ok(updatedMovel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        try {
            movelService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
