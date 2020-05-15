package br.com.treinamento.cleanarch.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEntity {

    private Long id;

    private String logradouro;

    private String numero;

    private Integer cep;

    private BairroEntity bairro;
}