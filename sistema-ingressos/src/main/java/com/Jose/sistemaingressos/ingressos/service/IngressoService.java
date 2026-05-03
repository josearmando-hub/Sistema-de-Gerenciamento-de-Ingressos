package com.Jose.sistemaingressos.ingressos.service;

import com.Jose.sistemaingressos.ingressos.model.Ingresso;
import com.Jose.sistemaingressos.ingressos.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository repository;

    public Ingresso salvar(Ingresso ingresso) {
        // Quando compra, o ingresso vai para PAGO ou EMITIDO. Vamos colocar EMITIDO direto para simplificar o fluxo.
        ingresso.setEstado(com.Jose.sistemaingressos.ingressos.model.EstadoIngresso.EMITIDO);
        System.out.println(ingresso.imprimirIngresso());
        return repository.save(ingresso);
    }

    public List<Ingresso> listarTodos() {
        return repository.findAll();
    }

    public Ingresso buscarPorId(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));
    }

    public double calcularValor(String id) {
        Ingresso ingresso = buscarPorId(id);
        return ingresso.calcularValor();
    }

    public List<Ingresso> buscarPorUsuario(String usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    // NOVA FUNÇÃO: Fluxo de Cancelamento
    // NOVA FUNÇÃO: Fluxo de Cancelamento
    public Ingresso cancelar(String ingressoId, String usuarioId) {
        Ingresso ingresso = buscarPorId(ingressoId);

        // 1. Verifica se o ingresso pertence a quem está tentando cancelar
        if (!ingresso.getUsuarioId().equals(usuarioId)) {
            throw new RuntimeException("Você não tem permissão para cancelar este ingresso.");
        }

        // 2. Verifica se já está cancelado ou devolvido
        if (ingresso.getEstado() == com.Jose.sistemaingressos.ingressos.model.EstadoIngresso.CANCELADO ||
                ingresso.getEstado() == com.Jose.sistemaingressos.ingressos.model.EstadoIngresso.DEVOLVIDO) {
            throw new RuntimeException("Este ingresso já encontra-se cancelado ou devolvido.");
        }

        // 3. Verifica a política de prazo (Só verifica se a data não for nula!)
        if (ingresso.getDataEvento() != null && ingresso.getDataEvento().isBefore(java.time.LocalDateTime.now())) {
            throw new RuntimeException("Cancelamento recusado: O evento já aconteceu.");
        }

        // 4. Atualizar Status e Salvar
        ingresso.setEstado(com.Jose.sistemaingressos.ingressos.model.EstadoIngresso.CANCELADO);
        Ingresso ingressoCancelado = repository.save(ingresso);

        // 5. Simulação do último bloco verde do fluxograma (Enviar E-mail)
        System.out.println("=====================================================");
        System.out.println("E-MAIL ENVIADO: Cancelamento confirmado para o ingresso " + ingressoId);
        System.out.println("=====================================================");

        return ingressoCancelado;
    }

    // NOVA FUNÇÃO: Fluxo de Devolução (Reembolso)
    public Ingresso devolver(String ingressoId, String usuarioId) {
        Ingresso ingresso = buscarPorId(ingressoId);

        // 1. Verifica se o ingresso pertence ao usuário
        if (!ingresso.getUsuarioId().equals(usuarioId)) {
            throw new RuntimeException("Você não tem permissão para devolver este ingresso.");
        }

        // 2. Verifica o status atual
        if (ingresso.getEstado() == com.Jose.sistemaingressos.ingressos.model.EstadoIngresso.CANCELADO ||
                ingresso.getEstado() == com.Jose.sistemaingressos.ingressos.model.EstadoIngresso.DEVOLVIDO) {
            throw new RuntimeException("Este ingresso já está cancelado ou devolvido.");
        }

        // 3. Política de devolução: não pode devolver se o evento já passou
        if (ingresso.getDataEvento() != null && ingresso.getDataEvento().isBefore(java.time.LocalDateTime.now())) {
            throw new RuntimeException("Devolução recusada: O evento já aconteceu ou o prazo expirou.");
        }

        // 4. Atualizar Status para DEVOLVIDO e Salvar
        ingresso.setEstado(com.Jose.sistemaingressos.ingressos.model.EstadoIngresso.DEVOLVIDO);
        Ingresso ingressoDevolvido = repository.save(ingresso);

        // 5. Simulação do processo de pagamento e e-mail (Blocos do diagrama)
        System.out.println("=====================================================");
        System.out.println("SISTEMA DE PAGAMENTO: Estorno no valor de R$ " + ingresso.calcularValor() + " processado.");
        System.out.println("E-MAIL ENVIADO: Confirmação de devolução do ingresso " + ingressoId);
        System.out.println("=====================================================");

        return ingressoDevolvido;
    }
}