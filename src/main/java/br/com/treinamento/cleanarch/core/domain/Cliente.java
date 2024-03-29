package br.com.treinamento.cleanarch.core.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    
    private Long id;

    private String nome;

    private String telefone;

    private List<Endereco> enderecos;

    private Integer documento;

    private String email;

    private LocalDate dataNascimento;
}