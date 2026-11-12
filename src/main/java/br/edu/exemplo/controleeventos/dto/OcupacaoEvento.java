package br.edu.exemplo.controleeventos.dto;

public record OcupacaoEvento(
        String titulo,
        Integer vagas,
        long inscritos,
        long disponiveis) {
}
