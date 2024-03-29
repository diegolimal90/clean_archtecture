package br.com.treinamento.cleanarch.dataprovider.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.treinamento.cleanarch.core.domain.Cliente;
import br.com.treinamento.cleanarch.core.domain.Endereco;
import br.com.treinamento.cleanarch.dataprovider.entity.ClienteTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;

public class ClienteTableMapper {
    public static ClienteTable to(Cliente entity){
        return Optional.ofNullable(entity).map(cliente -> ClienteTable.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .enderecos(toEnderecos(cliente.getEnderecos()))
                .documento(cliente.getDocumento())
                .email(cliente.getEmail())
                .dataNascimento(cliente.getDataNascimento())
            .build())
            .orElse(ClienteTable.builder().build());
    }

    private static List<EnderecoTable> toEnderecos(List<Endereco> enderecos){
        List<EnderecoTable> listaEnderecos = new ArrayList<>();

        for(Endereco endereco : enderecos){
            listaEnderecos.add(EnderecoTableMapper.to(endereco));
        }

        return listaEnderecos;
    }

    /**
     
        FROM

     */

    public static Cliente from(ClienteTable table){
        return Optional.ofNullable(table).map(cliente -> Cliente.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .enderecos(fromEnderecos(cliente.getEnderecos()))
                .documento(cliente.getDocumento())
                .email(cliente.getEmail())
                .dataNascimento(cliente.getDataNascimento())
            .build())
            .orElse(Cliente.builder().build());
    }

    private static List<Endereco> fromEnderecos(List<EnderecoTable> enderecos){
        List<Endereco> listaEnderecos = new ArrayList<>();

        for(EnderecoTable endereco : enderecos){
            listaEnderecos.add(EnderecoTableMapper.from(endereco));
        }

        return listaEnderecos;
    }
}