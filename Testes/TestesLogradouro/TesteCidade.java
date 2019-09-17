package TestesLogradouro;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Entites.Logradouro.Cidade;
import Model.Entites.Logradouro.Endereco;

public class TesteCidade {

	@Test
	public void testAddEndereco() {
		int qtdEnderecoEsperado = 1;
		Cidade cidade = new Cidade(1, "aracaju", "aju", null);
		Endereco endereco = new Endereco("oceanica", 1077, "atalaia", "49035005", cidade, null);
		cidade.addEnderecoLista(endereco);
		assertEquals(qtdEnderecoEsperado, cidade.getListaEndereco().size());
		
	}

	@Test
	public void testNaoAddEnderecoRepetido() {
		int qtdEnderecoEsperado = 1;
		Cidade cidade = new Cidade(1, "aracaju", "aju", null);
		Endereco endereco = new Endereco("oceanica", 1077, "atalaia", "49035005", cidade, null);
		Endereco endereco2 = new Endereco("oceanica", 1077, "atalaia", "49035005", cidade, null);
		cidade.addEnderecoLista(endereco);
		cidade.addEnderecoLista(endereco2);
		assertEquals(qtdEnderecoEsperado, cidade.getListaEndereco().size());
	}

}
