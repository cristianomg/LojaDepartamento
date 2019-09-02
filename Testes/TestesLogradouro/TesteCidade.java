package TestesLogradouro;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Entites.Logradouro.Cidade;
import Model.Entites.Logradouro.Endereco;

public class TesteCidade {

	@Test
	public void testAddEndereco() {
		int qtdEndere�oEsperado = 1;
		Cidade cidade = new Cidade(1, "aracaju", "aju", null);
		Endereco endereco = new Endereco(0, "oceanica", 1077, "atalaia", "49035005", cidade);
		cidade.addEnderecoLista(endereco);
		assertEquals(qtdEndere�oEsperado, cidade.getListaEndereco().size());
		
	}

	@Test
	public void testNaoAddEnderecoRepetido() {
		int qtdEndere�oEsperado = 1;
		Cidade cidade = new Cidade(1, "aracaju", "aju", null);
		Endereco endereco = new Endereco(0, "oceanica", 1077, "atalaia", "49035005", cidade);
		Endereco endereco2 = new Endereco(0, "oceanica", 1077, "atalaia", "49035005", cidade);
		cidade.addEnderecoLista(endereco);
		cidade.addEnderecoLista(endereco2);
		assertEquals(qtdEndere�oEsperado, cidade.getListaEndereco().size());
	}

}
