package br.com.treinamento.cleanarch.dataprovider;

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

import br.com.treinamento.cleanarch.configuration.exception.DataBaseException;
import br.com.treinamento.cleanarch.core.domain.Bairro;
import br.com.treinamento.cleanarch.core.domain.Cidade;
import br.com.treinamento.cleanarch.core.domain.Cliente;
import br.com.treinamento.cleanarch.core.domain.Endereco;
import br.com.treinamento.cleanarch.core.domain.Estado;
import br.com.treinamento.cleanarch.dataprovider.entity.BairroTable;
import br.com.treinamento.cleanarch.dataprovider.entity.CidadeTable;
import br.com.treinamento.cleanarch.dataprovider.entity.ClienteTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EstadoTable;
import br.com.treinamento.cleanarch.dataprovider.repository.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteDataProviderTest {

	@InjectMocks
	private ClienteDataProvider clienteDataProvider;
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@Test
	public void cadastrarCliente_success() {
		// Lista de enderecos que serão mapeadas atráves do entity
		List<EnderecoTable> enderecos = new ArrayList<>();
		enderecos.add(EnderecoTable.builder()
				.bairro(BairroTable.builder()
						.cidade(CidadeTable.builder()
								.estado(EstadoTable.builder().build())
						.build())
				.build())
		.build());
		
		// Lista de endereços que deve ser retornada após o repository.save
		List<EnderecoTable> enderecosSalvos = new ArrayList<>();
		enderecosSalvos.add(EnderecoTable.builder()
				.id(1L)
				.bairro(BairroTable.builder()
						.id(1L)
						.cidade(CidadeTable.builder()
								.id(1L)
								.estado(EstadoTable.builder()
										.id(1L)
								.build())
						.build())
				.build())
		.build());
		
		
		ClienteTable cliente = ClienteTable.builder()
				.nome("Victor")
				.enderecos(enderecos)
				.build();
		
		ClienteTable clienteSalvo = ClienteTable.builder()
				.id(1L)
				.enderecos(enderecosSalvos)
				.nome("Victor")
				.build();
		
		// Lista de endereços que será associada ao clienteEntity
		List<Endereco> enderecosEntity = new ArrayList<>();
		enderecosEntity.add(Endereco.builder()
				.bairro(Bairro.builder()
						.cidade(Cidade.builder()
								.estado(Estado.builder().build())
						.build())
				.build())
		.build());
		
		Cliente entity = Cliente.builder()
				.nome("Victor")
				.enderecos(enderecosEntity)
				.build();
		
		Mockito.when(clienteRepository.save(cliente)).thenReturn(clienteSalvo);
		
		Cliente response = clienteDataProvider.cadastrarCliente(entity);
		
		Assert.assertThat(response.getId(), Matchers.notNullValue());
	}
	
	@Test(expected = DataBaseException.class)
	public void cadastrarCliente_dataBaseException() {
		
		// Enviando um cliente que não possui uma lista de endereços, para forçar a exception
		Cliente entity = Cliente.builder()
				.nome("Victor")
				.build();
		
		clienteDataProvider.cadastrarCliente(entity);
	}
}
