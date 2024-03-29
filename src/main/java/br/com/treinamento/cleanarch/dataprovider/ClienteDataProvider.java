package br.com.treinamento.cleanarch.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinamento.cleanarch.configuration.exception.DataBaseException;
import br.com.treinamento.cleanarch.core.domain.Cliente;
import br.com.treinamento.cleanarch.gateway.ClienteGateway;
import br.com.treinamento.cleanarch.dataprovider.entity.ClienteTable;
import br.com.treinamento.cleanarch.dataprovider.mapper.ClienteTableMapper;
import br.com.treinamento.cleanarch.dataprovider.repository.ClienteRepository;

@Component("clienteDataProvider")
public class ClienteDataProvider implements ClienteGateway{
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente cadastrarCliente(Cliente entity){
        try{
            ClienteTable table = ClienteTableMapper.to(entity);

            table = clienteRepository.save(table);

            entity = ClienteTableMapper.from(table);

            return entity;
        }catch(Exception e){
            throw new DataBaseException("[E01] Falha no cadastro do cliente!");
        }

    }

}