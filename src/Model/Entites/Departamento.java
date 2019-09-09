package Model.Entites;

import java.util.ArrayList;

import Exceptions.ObjetoExisteException;

public class Departamento {
	private static int contador;
	private int id;
	private String nome;
	private String sigla;
	private ArrayList<Funcionario> listFuncionario = new ArrayList<Funcionario>();
	
	public Departamento() {
		this.id = contador;
		contador ++;
	}
	
	public Departamento(String nome, String sigla) {
		this();
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public Departamento(int id, String nome, String sigla, ArrayList<Funcionario> listFuncionario) {
		this(nome, sigla);
		this.id = id;
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
			throw new ObjetoExisteException(String.format("O funcionario %s ja esta cadastrado", funcionario.getNome())); 
		}
	}
	
}
