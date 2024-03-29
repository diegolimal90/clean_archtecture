package br.com.treinamento.cleanarch.gateway;

import java.util.List;

import br.com.treinamento.cleanarch.core.domain.Endereco;

public interface EnderecoGateway {
    Endereco cadastrarEndereco(Endereco entity);

    List<Endereco> buscarEndereco(Integer cep);
}