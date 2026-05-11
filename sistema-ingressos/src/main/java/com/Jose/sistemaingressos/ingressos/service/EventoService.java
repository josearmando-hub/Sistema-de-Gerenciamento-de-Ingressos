package com.Jose.sistemaingressos.ingressos.service;

import com.Jose.sistemaingressos.ingressos.model.Evento;
import com.Jose.sistemaingressos.ingressos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public Evento salvar(Evento evento) {
        validar(evento);
        return repository.save(evento);
    }

    public List<Evento> listarDisponiveis() {
        return repository.findByAtivoTrueOrderByDataHoraAsc();
    }

    public List<Evento> listarTodos() {
        return repository.findAll();
    }

    public Evento buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));
    }

    private void validar(Evento evento) {
        if (evento.getNome() == null || evento.getNome().isBlank()) {
            throw new RuntimeException("Informe o nome do evento.");
        }
        if (evento.getDescricao() == null || evento.getDescricao().isBlank()) {
            throw new RuntimeException("Informe a descrição do evento.");
        }
        if (evento.getLocal() == null || evento.getLocal().isBlank()) {
            throw new RuntimeException("Informe o local do evento.");
        }
        if (evento.getDataHora() == null || evento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("A data do evento deve ser futura.");
        }
        if (evento.getQuantidadeIngressosDisponiveis() < 0) {
            throw new RuntimeException("A quantidade de ingressos não pode ser negativa.");
        }
        if (evento.getValorIngresso() <= 0) {
            throw new RuntimeException("O valor do ingresso deve ser maior que zero.");
        }
    }
}
