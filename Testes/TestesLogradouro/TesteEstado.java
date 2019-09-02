package TestesLogradouro;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Entites.Logradouro.Cidade;
import Model.Entites.Logradouro.Estado;

public class TesteEstado {

	@Test
	public void testarAddCidade() {
		System.out.println("Test ADD Cidade");
		int qtdCidadesEsperado = 1;
		Estado estado = new Estado(1,"sergipe", "se");
		System.out.println("Estado: "+ estado.getNome());
		Cidade cd = new Cidade(1,"aracaju","aju",estado);
		System.out.println("Cidade: "+ cd.getNome());
		estado.addCidadeLista(cd);
		System.out.println(estado.getListaCidade().size());
		assertEquals(qtdCidadesEsperado, estado.getListaCidade().size());
		
	}
	@Test
	public void testarNaoAddCidadeRepetidas() {
		System.out.println("Teste para restringir cidades repetidas");
		int qtdCidadesEsperado = 1;
		Estado estado = new Estado(1,"sergipe", "se");
		Cidade c1 = new Cidade(1,"aracaju","aju", estado);
		Cidade c2 = new Cidade(1,"aracaju", "aju", estado);
		estado.addCidadeLista(c1);
		estado.addCidadeLista(c2);
		System.out.println(estado.getListaCidade().size());
		assertEquals(qtdCidadesEsperado, estado.getListaCidade().size());
		
	}

}
