package br.com.treinamento.cleanarch.entrypoint.mapper;

import java.util.Optional;

import br.com.treinamento.cleanarch.core.domain.Bairro;
import br.com.treinamento.cleanarch.core.domain.Cidade;
import br.com.treinamento.cleanarch.core.domain.Endereco;
import br.com.treinamento.cleanarch.core.domain.Estado;
import br.com.treinamento.cleanarch.entrypoint.entity.EnderecoRequestHttpModel;

public class EnderecoRequestHttpModelMapper {
    public static EnderecoRequestHttpModel to(Endereco entity){
        return Optional.ofNullable(entity).map(endereco -> 
            EnderecoRequestHttpModel.builder()
                .logradouro(endereco.getLogradouro())
                .bairro(endereco.getBairro().getNome())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .cidade(endereco.getBairro().getCidade().getNome())
                .estado(endereco.getBairro().getCidade().getEstado().getNome())
            .build())
            .orElse(EnderecoRequestHttpModel.builder().build());
    }

    public static Endereco from(EnderecoRequestHttpModel httpModel){
        return Optional.ofNullable(httpModel).map(endereco -> 
            Endereco.builder()
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .bairro(
                    Bairro.builder()
                        .nome(endereco.getBairro())
                        .cidade(
                            Cidade.builder()
                                .nome(endereco.getCidade())
                                .estado(
                                    Estado.builder()
                                        .nome(endereco.getEstado())
                                    .build())
                            .build())
                    .build())
            .build())
            .orElse(Endereco.builder()
                        .bairro(Bairro.builder()
                            .cidade(Cidade.builder()
                                .estado(Estado.builder().build())
                            .build())
                        .build())
                    .build());
    }
}