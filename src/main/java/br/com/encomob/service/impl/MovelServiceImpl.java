package br.com.encomob.service.impl;

import br.com.encomob.domain.Movel;
import br.com.encomob.repository.MovelRepository;
import br.com.encomob.service.MovelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovelServiceImpl implements MovelService {

    private final MovelRepository movelRepository;

    public MovelServiceImpl(MovelRepository movelRepository) {
        this.movelRepository = movelRepository;
    }

    @Override
    public Movel save(Movel movel) {
        return movelRepository.save(movel);
    }

    @Override
    public Optional<Movel> findById(UUID id) {
        return movelRepository.findById(id);
    }

    @Override
    public List<Movel> findAll() {
        return movelRepository.findAll();
    }

    @Override
    public Movel update(Movel movel) {
        if (!movelRepository.existsById(movel.getId())) {
            throw new IllegalArgumentException("Móvel com ID " + movel.getId() + " não encontrado.");
        }
        return movelRepository.save(movel);
    }

    @Override
    public void deleteById(UUID id) {
        if (!movelRepository.existsById(id)) {
            throw new IllegalArgumentException("Móvel com ID " + id + " não encontrado.");
        }
        movelRepository.deleteById(id);
    }
}
