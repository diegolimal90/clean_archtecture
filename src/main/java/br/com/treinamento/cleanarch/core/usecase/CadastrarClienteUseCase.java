package br.com.treinamento.cleanarch.core.usecase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinamento.cleanarch.core.domain.Bairro;
import br.com.treinamento.cleanarch.core.domain.Cidade;
import br.com.treinamento.cleanarch.core.domain.Cliente;
import br.com.treinamento.cleanarch.core.domain.Endereco;
import br.com.treinamento.cleanarch.core.domain.Estado;
import br.com.treinamento.cleanarch.gateway.ClienteGateway;
import br.com.treinamento.cleanarch.gateway.EnderecoGateway;

@Service("cadastrarClienteUseCase")
public class CadastrarClienteUseCase implements ClienteGateway {

    @Autowired
    private ClienteGateway clienteDataProvider;

    @Autowired
    private EnderecoGateway enderecoGateway;

    @Override
    public Cliente cadastrarCliente(Cliente domain) {
//		Cria a lista que irá armazenar endereços que serão salvos
        List<Endereco> enderecos = new ArrayList<>();

        //		percorre os endereços que serão salvos
        for (Endereco endereco : domain.getEnderecos()) {
            //			efetua busca de endereços cadastrados
            List<Endereco> enderecosCad = enderecoGateway.buscarEndereco(endereco.getCep());
            //			verefica se existe retornou algum endereco
            if (!enderecosCad.isEmpty() && enderecosCad.get(0).getId() > 0) {

                Endereco enderecoExistente = enderecosCad.get(0);
                //				criou a entidade enderecoExistente que sera salva no banco de dados
                endereco = Endereco.builder()
                        .logradouro(enderecoExistente.getLogradouro())
                        .numero(endereco.getNumero())
                        .cep(enderecoExistente.getCep())
                        .bairro(Bairro.builder()
                                .id(enderecoExistente.getId())
                                .nome(enderecoExistente.getBairro().getNome())
                                .cidade(Cidade.builder()
                                        .id(enderecoExistente.getId())
                                        .nome(enderecoExistente.getBairro().getCidade().getNome())
                                        .estado(Estado.builder()
                                                .id(enderecoExistente.getId())
                                                .nome(enderecoExistente.getBairro().getCidade().getEstado().getNome())
                                                .build())
                                        .build())
                                .build())
                        .build();
            }
            // Salva na lista o endereço que não está cadastrado na base de dados
            enderecos.add(endereco);

        }

        domain.setEnderecos(enderecos);

        Cliente clienteSalvo = clienteDataProvider.cadastrarCliente(domain);

        // forEach para salvar todos os endereços da lista, após salvar o cliente referente a cada endereço
        enderecos.forEach(endereco -> endereco = enderecoGateway.cadastrarEndereco(endereco));

        return clienteSalvo;
    }
}