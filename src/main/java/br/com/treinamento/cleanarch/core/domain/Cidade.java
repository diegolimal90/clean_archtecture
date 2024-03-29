package br.com.treinamento.cleanarch.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cidade {
    private Long id;

    private String nome;

    private Estado estado;
}