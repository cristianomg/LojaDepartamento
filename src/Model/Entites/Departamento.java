package Model.Entites;

import java.util.ArrayList;

import Exceptions.ObjetoExisteException;

public class Departamento {
	private int id;
	private String nome;
	private String sigla;
	private ArrayList<Funcionario> listFuncionario = new ArrayList<Funcionario>();
	
	public Departamento(int id, String nome, String sigla, ArrayList<Funcionario> listFuncionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.listFuncionario = listFuncionario;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public ArrayList<Funcionario> getListFuncionario() {
		return listFuncionario;
	}
	public void setListFuncionario(ArrayList<Funcionario> listFuncionario) {
		this.listFuncionario = listFuncionario;
	}
	
	public void addFuncionarioList(Funcionario funcionario) throws ObjetoExisteException {
		boolean contem = false;
		for (Funcionario f: this.listFuncionario) {
			if (funcionario.getMatricula() == f.getMatricula()) {
				contem = true;
			}
		}
		if (!contem) {
			this.listFuncionario.add(funcionario);
		}
		else {
			throw new ObjetoExisteException(String.format("O funcionario %s já está cadastrado", funcionario.getNome())); 
		}
	}
	
}
