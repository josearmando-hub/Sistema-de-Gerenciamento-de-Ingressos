package com.Jose.sistemaingressos.ingressos.service;

import com.Jose.sistemaingressos.ingressos.model.*;
import com.Jose.sistemaingressos.ingressos.repository.EventoRepository;
import com.Jose.sistemaingressos.ingressos.repository.IngressoRepository;
import com.Jose.sistemaingressos.ingressos.repository.ReservaRepository;
import com.Jose.sistemaingressos.ingressos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private QrCodeService qrCodeService;

    public Reserva reservar(String clienteId, String eventoId, String tipoIngresso) {
        Usuario cliente = usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));

        if (reservaRepository.existsByClienteIdAndEventoIdAndStatusIn(
                clienteId,
                eventoId,
                List.of(StatusReserva.RESERVADA, StatusReserva.CONFIRMADA, StatusReserva.UTILIZADA))) {
            throw new RuntimeException("Este cliente já possui uma reserva para este evento.");
        }

        evento.reservarUmaVaga();
        eventoRepository.save(evento);

        Ingresso ingresso = criarIngresso(tipoIngresso);
        ingresso.setUsuarioId(cliente.getId());
        ingresso.setEventoId(evento.getId());
        ingresso.setEvento(evento.getNome());
        ingresso.setDataEvento(evento.getDataHora());
        ingresso.setValorBase(evento.getValorIngresso());
        ingresso.setEstado(EstadoIngresso.RESERVADO);
        String codigoQr = gerarCodigoQr();
        ingresso.setCodigoQr(codigoQr);
        ingresso.setQrCodeBase64(qrCodeService.gerarPngBase64(codigoQr));
        Ingresso ingressoSalvo = ingressoRepository.save(ingresso);

        Reserva reserva = new Reserva();
        reserva.setClienteId(cliente.getId());
        reserva.setEventoId(evento.getId());
        reserva.setIngressoId(ingressoSalvo.getId());
        reserva.setStatus(StatusReserva.RESERVADA);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarPorCliente(String clienteId) {
        return reservaRepository.findByClienteIdOrderByDataReservaDesc(clienteId);
    }

    public Reserva cancelar(String reservaId, String clienteId) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada."));
        if (!reserva.getClienteId().equals(clienteId)) {
            throw new RuntimeException("Você não tem permissão para cancelar esta reserva.");
        }
        if (reserva.getStatus() == StatusReserva.CANCELADA || reserva.getStatus() == StatusReserva.UTILIZADA) {
            throw new RuntimeException("Esta reserva não pode mais ser cancelada.");
        }

        reserva.setStatus(StatusReserva.CANCELADA);
        Ingresso ingresso = ingressoRepository.findById(reserva.getIngressoId())
                .orElseThrow(() -> new RuntimeException("Ingresso da reserva não encontrado."));
        ingresso.setEstado(EstadoIngresso.CANCELADO);
        ingressoRepository.save(ingresso);

        Evento evento = eventoRepository.findById(reserva.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento da reserva não encontrado."));
        evento.liberarUmaVaga();
        eventoRepository.save(evento);

        return reservaRepository.save(reserva);
    }

    private Ingresso criarIngresso(String tipoIngresso) {
        if ("VIP".equalsIgnoreCase(tipoIngresso)) {
            IngressoVIP ingresso = new IngressoVIP();
            ingresso.setTaxaVIP(30.0);
            return ingresso;
        }
        if ("MEIA".equalsIgnoreCase(tipoIngresso)) {
            IngressoMeia ingresso = new IngressoMeia();
            ingresso.setPercentualDesconto(50.0);
            return ingresso;
        }
        return new IngressoNormal();
    }

    private String gerarCodigoQr() {
        return "QR-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
    }
}
