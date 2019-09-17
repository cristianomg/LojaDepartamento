package Model.Entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Exceptions.ObjetoExisteException;
import Model.DAO.ProdutoDAO;

public class Departamento implements Serializable {
	private static final long serialVersionUID = 6618504795044699367L;
	private int id;
	private String nome;
	private String sigla;
	private Funcionario chefe;
	private ArrayList<Funcionario> listFuncionario = new ArrayList<Funcionario>();
	
	public Departamento(int id) {
		this.id = id;
		}
	
	public Departamento(int id, String nome, String sigla) {
		this(id);
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public Departamento(int id, String nome, String sigla, ArrayList<Funcionario> listFuncionario) {
		this(id, nome, sigla);
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
			throw new ObjetoExisteException(String.format("O funcionario %s já esta cadastrado", funcionario.getNome())); 
		}
	}
	public List<Produto> getProdutos(){
		List<Produto> produtos = ProdutoDAO.getInstance().getProdutoDepartamento(this.id);
		return produtos;
	}

	public Funcionario getChefe() {
		return chefe;
	}

	public void setChefe(Funcionario funcionario) {
		if (funcionario.isEnsinoSuperior()) {
			funcionario.setChefe(true);
			this.chefe = funcionario;
		}
	}
	
}
