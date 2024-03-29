package br.com.treinamento.cleanarch.core.usecase;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.treinamento.cleanarch.core.domain.Bairro;
import br.com.treinamento.cleanarch.core.domain.Cidade;
import br.com.treinamento.cleanarch.core.domain.Cliente;
import br.com.treinamento.cleanarch.core.domain.Endereco;
import br.com.treinamento.cleanarch.core.domain.Estado;
import br.com.treinamento.cleanarch.dataprovider.ClienteDataProvider;
import br.com.treinamento.cleanarch.dataprovider.EnderecoDataProvider;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarClienteUseCaseTest {

	@InjectMocks
	private CadastrarClienteUseCase clienteUseCase;
	
	@Mock
    private ClienteDataProvider clienteDataprovider;

    @Mock
    private EnderecoDataProvider enderecoDataprovider;
    
    @Test
    public void cadastrarClienteUseCase_success_with_feign() {
    	
    	List<Endereco> enderecosFeign = new ArrayList<>();
    	enderecosFeign.add(Endereco.builder()
    			.id(1L)
    			.cep(111)
				.bairro(Bairro.builder()
						.id(1L)
						.cidade(Cidade.builder()
								.id(1L)
								.estado(Estado.builder().id(1L).build())
						.build())
				.build())
		.build());
    	
    	
    	List<Endereco> enderecosSalvo = new ArrayList<>();
    	enderecosSalvo.add(Endereco.builder()
    			.id(1L)
    			.cep(111)
				.bairro(Bairro.builder()
						.id(1L)
						.cidade(Cidade.builder()
								.id(1L)
								.estado(Estado.builder().id(1L).build())
						.build())
				.build())
		.build());
    	
    	List<Endereco> enderecosNovo = new ArrayList<>();
    	enderecosNovo.add(Endereco.builder()
    			.cep(111)
				.bairro(Bairro.builder()
						.cidade(Cidade.builder()
								.estado(Estado.builder().build())
						.build())
				.build())
		.build());
    	
    	Cliente clienteTeste = Cliente.builder()
    			.nome("Ricardo")
    			.enderecos(enderecosNovo)
    			.build();
    	
    	Cliente clienteSalvo = Cliente.builder()
    			.id(1L)
    			.nome("Ricardo")
    			.enderecos(enderecosSalvo)
    			.build();
    	
    	Mockito.when(enderecoDataprovider.buscarEndereco(Mockito.anyInt())).thenReturn(enderecosFeign);
    	Mockito.when(enderecoDataprovider.cadastrarEndereco(Mockito.any(Endereco.class))).thenReturn(enderecosSalvo.get(0));
    	Mockito.when(clienteDataprovider.cadastrarCliente(Mockito.any(Cliente.class))).thenReturn(clienteSalvo);
    	
    	Cliente response = clienteUseCase.cadastrarCliente(clienteTeste);
    	
    	Assert.assertThat(response.getId(), Matchers.notNullValue());
    }
    
    @Test
    public void cadastrarClienteUseCase_success_empty_feign() {
    	
    	List<Endereco> enderecosSalvo = new ArrayList<>();
    	enderecosSalvo.add(Endereco.builder()
    			.id(1L)
    			.cep(111)
				.bairro(Bairro.builder()
						.id(1L)
						.cidade(Cidade.builder()
								.id(1L)
								.estado(Estado.builder().id(1L).build())
						.build())
				.build())
		.build());
    	
    	List<Endereco> enderecosNovo = new ArrayList<>();
    	enderecosNovo.add(Endereco.builder()
    			.cep(111)
				.bairro(Bairro.builder()
						.cidade(Cidade.builder()
								.estado(Estado.builder().build())
						.build())
				.build())
		.build());
    	
    	Cliente clienteTeste = Cliente.builder()
    			.nome("Ricardo")
    			.enderecos(enderecosNovo)
    			.build();
    	
    	Cliente clienteSalvo = Cliente.builder()
    			.id(1L)
    			.nome("Ricardo")
    			.enderecos(enderecosSalvo)
    			.build();
    	
    	Mockito.when(enderecoDataprovider.buscarEndereco(Mockito.anyInt())).thenReturn(new ArrayList<>());
    	Mockito.when(enderecoDataprovider.cadastrarEndereco(Mockito.any(Endereco.class))).thenReturn(enderecosNovo.get(0));
    	Mockito.when(clienteDataprovider.cadastrarCliente(Mockito.any(Cliente.class))).thenReturn(clienteSalvo);
    	
    	Cliente response = clienteUseCase.cadastrarCliente(clienteTeste);
    	
    	Assert.assertThat(response.getId(), Matchers.notNullValue());
    }
}
