package br.com.encomob.service;

import br.com.encomob.domain.Movel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovelService {

    Movel save(Movel movel);
    Optional<Movel> findById(UUID id);
    List<Movel> findAll();
    Movel update(Movel movel);
    void deleteById(UUID id);
}
