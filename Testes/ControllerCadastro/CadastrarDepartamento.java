package ControllerCadastro;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import Model.Entites.Departamento;

public class CadastrarDepartamento {
	List<Departamento> departamentos = new ArrayList<Departamento>();

	public String cadastrarDepartamento(HashMap<String, String> dadosDepartamento) {
		String nome = dadosDepartamento.get("nome");
		String sigla = dadosDepartamento.get("sigla");
		Departamento dp = new Departamento(nome, sigla);
		departamentos.add(dp);
		return "Departamento Cadastrado com sucesso!!!";
	}
	
	@Test
	public void test() {
		String expected = "Departamento Cadastrado com sucesso!!!";
		HashMap<String, String> dadosDepartamento = new HashMap<String, String>();
		String actual = cadastrarDepartamento(dadosDepartamento);
		assertEquals(expected, actual);
		assertEquals(1, departamentos.size());
	}

}
