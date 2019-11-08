package ControllerCadastro;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Model.Entites.Departamento;
import Model.Entites.Funcionario;

public class CadastroFuncionario {
	List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	List<Departamento> departamentos = new ArrayList<Departamento>();

	public String cadastrarFuncionario(int idDepartamento, String nomeFuncionario, String matriculaFuncionario,
			String senhaFuncionario) {
			Departamento departamento = departamentos.get(idDepartamento);
			String nome = nomeFuncionario;
			String matricula = matriculaFuncionario;
			String senha = senhaFuncionario;
			String cursoSuperior = "n";
			Funcionario f = new Funcionario(nome, matricula, senha);
			if (!funcionarios.contains(f)) {
				if (cursoSuperior.equals("s")) {
					f.setEnsinoSuperior(true);
				}
				f.setDepartamento(departamento);
				funcionarios.add(f);
				return "Funcionario Cadastrado com sucesso!!!";
			} else {
				return "Erro: Matricula já cadastrada, insira uma nova.";
			}
	
	}

	@Test
	public void testCadastroFuncionario() {
		Departamento d = new Departamento("eletronico", "sigla");
		departamentos.add(d);
		String esperado = "Funcionario Cadastrado com sucesso!!!";
		String atual = cadastrarFuncionario(0, "teste1", "123123", "123123");
		assertEquals(esperado, atual);
	}

}
