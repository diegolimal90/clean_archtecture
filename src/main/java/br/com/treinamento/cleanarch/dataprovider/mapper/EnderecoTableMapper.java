package br.com.treinamento.cleanarch.dataprovider.mapper;

import java.util.Optional;

import br.com.treinamento.cleanarch.core.domain.Bairro;
import br.com.treinamento.cleanarch.core.domain.Cidade;
import br.com.treinamento.cleanarch.core.domain.Endereco;
import br.com.treinamento.cleanarch.core.domain.Estado;
import br.com.treinamento.cleanarch.dataprovider.entity.BairroTable;
import br.com.treinamento.cleanarch.dataprovider.entity.CidadeTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EstadoTable;

public class EnderecoTableMapper {
    public static EnderecoTable to(Endereco entity){
        return Optional.ofNullable(entity).map(endereco -> 
            EnderecoTable.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .bairro(Optional.ofNullable(endereco.getBairro()).map(bairro -> 
                    BairroTable.builder()
                        .id(bairro.getId())
                        .nome(bairro.getNome())
                        .cidade(Optional.ofNullable(bairro.getCidade()).map(cidade ->
                            CidadeTable.builder()
                                .id(cidade.getId())
                                .nome(cidade.getNome())
                                .estado(Optional.ofNullable(cidade.getEstado()).map(estado ->
                                    EstadoTable.builder()
                                        .id(estado.getId())
                                        .nome(estado.getNome())
                                    .build())
                                    .orElse(EstadoTable.builder().build()))
                            .build())
                            .orElse(CidadeTable.builder()
                                    .estado(EstadoTable.builder().build())
                                .build()))
                    .build())
                    .orElse(BairroTable.builder()
                            .cidade(CidadeTable.builder()
                                    .estado(EstadoTable.builder().build())
                                .build())
                        .build()))
            .build())
            .orElse(EnderecoTable.builder()
                    .bairro(BairroTable.builder()
                        .cidade(CidadeTable.builder()
                            .estado(EstadoTable.builder().build())
                        .build())
                    .build())
                .build());
    }

    public static Endereco from(EnderecoTable table){
        return Optional.ofNullable(table).map(endereco -> 
            Endereco.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .bairro(Optional.ofNullable(endereco.getBairro()).map(bairro -> 
                    Bairro.builder()
                        .id(bairro.getId())
                        .nome(bairro.getNome())
                        .cidade(Optional.ofNullable(bairro.getCidade()).map(cidade ->
                            Cidade.builder()
                                .id(cidade.getId())
                                .nome(cidade.getNome())
                                .estado(Optional.ofNullable(cidade.getEstado()).map(estado ->
                                    Estado.builder()
                                        .id(estado.getId())
                                        .nome(estado.getNome())
                                    .build())
                                    .orElse(Estado.builder().build()))
                            .build())
                            .orElse(Cidade.builder()
                                    .estado(Estado.builder().build())
                                .build()))
                    .build())
                    .orElse(Bairro.builder()
                            .cidade(Cidade.builder()
                                    .estado(Estado.builder().build())
                                .build())
                        .build()))
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