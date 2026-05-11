package com.Jose.sistemaingressos.ingressos.service;

import com.Jose.sistemaingressos.ingressos.model.*;
import com.Jose.sistemaingressos.ingressos.repository.EventoRepository;
import com.Jose.sistemaingressos.ingressos.repository.IngressoRepository;
import com.Jose.sistemaingressos.ingressos.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public List<Ingresso> listarIngressosPorEvento(String eventoId) {
        return ingressoRepository.findByEventoId(eventoId);
    }

    public Ingresso verificarIngresso(String codigoQr) {
        return ingressoRepository.findByCodigoQr(codigoQr)
                .orElseThrow(() -> new RuntimeException("QR Code inválido ou ingresso não encontrado."));
    }

    public Ingresso utilizarIngresso(String codigoQr) {
        Ingresso ingresso = verificarIngresso(codigoQr);
        if (ingresso.getEstado() == EstadoIngresso.UTILIZADO || ingresso.getEstado() == EstadoIngresso.USADO) {
            throw new RuntimeException("Este ingresso já foi utilizado.");
        }
        if (ingresso.getEstado() == EstadoIngresso.CANCELADO || ingresso.getEstado() == EstadoIngresso.DEVOLVIDO) {
            throw new RuntimeException("Este ingresso não é válido para entrada.");
        }

        ingresso.setEstado(EstadoIngresso.UTILIZADO);
        ingresso.setDataUtilizacao(LocalDateTime.now());
        Ingresso ingressoAtualizado = ingressoRepository.save(ingresso);

        reservaRepository.findByIngressoId(ingresso.getId()).ifPresent(reserva -> {
            reserva.setStatus(StatusReserva.UTILIZADA);
            reservaRepository.save(reserva);
        });

        return ingressoAtualizado;
    }

    public Map<String, Object> relatorioPorEvento(String eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));

        Map<String, Object> relatorio = new LinkedHashMap<>();
        relatorio.put("evento", evento);
        relatorio.put("reservados", ingressoRepository.countByEventoIdAndEstado(eventoId, EstadoIngresso.RESERVADO));
        relatorio.put("confirmados", ingressoRepository.countByEventoIdAndEstado(eventoId, EstadoIngresso.CONFIRMADO));
        relatorio.put("utilizados", ingressoRepository.countByEventoIdAndEstado(eventoId, EstadoIngresso.UTILIZADO));
        relatorio.put("cancelados", ingressoRepository.countByEventoIdAndEstado(eventoId, EstadoIngresso.CANCELADO));
        relatorio.put("disponiveis", evento.getQuantidadeIngressosDisponiveis());
        return relatorio;
    }
}
