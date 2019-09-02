package Model.Entites;

import java.util.ArrayList;

public class Funcionario {
	private String nome;
	private String matricula;
	private String senha;
	private Departamento departamento;
	private ArrayList<Venda> listaVenda = new ArrayList<Venda>();
	
	public Funcionario(String nome) {
		this.nome = nome;
	}
	
	public Funcionario(String nome, String matricula) {
		this(nome);
		this.matricula = matricula;
	}
	
	public Funcionario(String nome, String matricula, String senha) {
		this(nome, matricula);
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public ArrayList<Venda> getListaVenda() {
		return listaVenda;
	}

	public void setListaVenda(ArrayList<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}
	
	
	
}
