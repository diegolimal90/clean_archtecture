package br.com.treinamento.cleanarch.entrypoint;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.treinamento.cleanarch.core.domain.Bairro;
import br.com.treinamento.cleanarch.core.domain.Cidade;
import br.com.treinamento.cleanarch.core.domain.Cliente;
import br.com.treinamento.cleanarch.core.domain.Endereco;
import br.com.treinamento.cleanarch.core.domain.Estado;
import br.com.treinamento.cleanarch.core.usecase.CadastrarClienteUseCase;
import br.com.treinamento.cleanarch.entrypoint.entity.ClienteRequestHttpModel;
import br.com.treinamento.cleanarch.entrypoint.entity.EnderecoRequestHttpModel;

@RunWith(MockitoJUnitRunner.class)
public class ClienteEntrypointTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	private ClienteEntrypoint clienteEntrypoint;
	
	@Mock
	private CadastrarClienteUseCase clienteUseCase;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.clienteEntrypoint).build();
	}
	
	@Test
	public void cadastrarCliente_status201() throws JsonProcessingException, Exception {
		
		List<EnderecoRequestHttpModel> enderecos = new ArrayList<>();
		enderecos.add(EnderecoRequestHttpModel.builder().build());
		
		ClienteRequestHttpModel clienteHttpModel = ClienteRequestHttpModel.builder()
			.nomeCliente("Matheus")
			.enderecos(enderecos)
			.build();
		
		List<Endereco> enderecosEntity = new ArrayList<>();
		enderecosEntity.add(Endereco.builder()
				.id(1L)
				.bairro(Bairro.builder()
						.id(1L)
						.cidade(Cidade.builder()
								.estado(Estado.builder().id(1L).build())
						.build())
				.build())
		.build());
		
		Cliente cliente = Cliente.builder()
			.id(1L)
			.nome("Matheus")
			.enderecos(enderecosEntity)
			.build();

		Mockito.when(clienteUseCase.cadastrarCliente(Mockito.any(Cliente.class))).thenReturn(cliente);
		
		this.mockMvc.perform(
			MockMvcRequestBuilders
			.post("/cliente/")
			.contentType(MediaType.APPLICATION_JSON)
			.content( new ObjectMapper().writeValueAsString(clienteHttpModel) )
			).andExpect(status().isCreated());
		
	}
}