package br.com.treinamento.cleanarch.entrypoint.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.treinamento.cleanarch.core.domain.Cliente;
import br.com.treinamento.cleanarch.core.domain.Endereco;
import br.com.treinamento.cleanarch.entrypoint.entity.ClienteRequestHttpModel;
import br.com.treinamento.cleanarch.entrypoint.entity.EnderecoRequestHttpModel;

public class ClienteRequestHttpModelMapper {
    public static ClienteRequestHttpModel to(Cliente entity){
        return Optional.ofNullable(entity).map(cliente -> ClienteRequestHttpModel.builder()
                .nomeCliente(cliente.getNome())
                .telefoneCliente(cliente.getTelefone())
                .enderecos(toEnderecos(cliente.getEnderecos()))
                .docCliente(cliente.getDocumento())
                .emailCliente(cliente.getEmail())
                .dataNascimento(cliente.getDataNascimento())
            .build())
            .orElse(ClienteRequestHttpModel.builder().build());
    }

    private static List<EnderecoRequestHttpModel> toEnderecos(List<Endereco> enderecosCliente){
        List<EnderecoRequestHttpModel> listaEnderecos = new ArrayList<>();

        for(Endereco endereco : enderecosCliente){
            listaEnderecos.add(EnderecoRequestHttpModelMapper.to(endereco));
        }

        return listaEnderecos;
    }

    public static Cliente from(ClienteRequestHttpModel httpModel){
        return Optional.ofNullable(httpModel).map(cliente -> 
            Cliente.builder()
                .nome(cliente.getNomeCliente())
                .telefone(cliente.getTelefoneCliente())
                .enderecos(fromEnderecos(cliente.getEnderecos()))
                .documento(cliente.getDocCliente())
                .email(cliente.getEmailCliente())
                .dataNascimento(cliente.getDataNascimento())
            .build())
            .orElse(Cliente.builder().build());
    }

    private static List<Endereco> fromEnderecos(List<EnderecoRequestHttpModel> enderecosCliente){
        List<Endereco> listaEnderecos = new ArrayList<>();

        for(EnderecoRequestHttpModel endereco : enderecosCliente){
            listaEnderecos.add(EnderecoRequestHttpModelMapper.from(endereco));
        }

        return listaEnderecos;
    }
}