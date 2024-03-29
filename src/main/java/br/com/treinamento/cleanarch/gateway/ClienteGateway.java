package br.com.treinamento.cleanarch.gateway;

import br.com.treinamento.cleanarch.core.domain.Cliente;

public interface ClienteGateway {
    Cliente cadastrarCliente(Cliente domain);
}