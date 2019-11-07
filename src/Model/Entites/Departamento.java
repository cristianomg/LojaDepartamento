package Model.Entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.ProdutoDAO;

public class Departamento implements Serializable {
	private static final long serialVersionUID = 6618504795044699367L;
	private int id;
	private String nome;
	private String sigla;
	private Funcionario chefe;
	private double percentualComissao;
	private ArrayList<Funcionario> listFuncionario = new ArrayList<Funcionario>();

	public Departamento() {
		this.id = hashCode();
		this.percentualComissao = 3;
	}

	public Departamento(String nome, String sigla) {
		this();
		this.nome = nome;
		this.sigla = sigla;
	}

	public Departamento(String nome, String sigla, ArrayList<Funcionario> listFuncionario) {
		this(nome, sigla);
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

	public void addFuncionarioList(Funcionario funcionario) {
		listFuncionario.add(funcionario);

	}
	
	public void removerFuncionarioList(Funcionario funcionario) {
		listFuncionario.remove(funcionario);
	}

	public List<Produto> getProdutos() {
		List<Produto> produtos = ProdutoDAO.getInstance().getProdutoDepartamento(this.id);
		return produtos;
	}

	public Funcionario getChefe() {
		return chefe;
	}

	public boolean setChefe(Funcionario funcionario) {
		if (funcionario.isEnsinoSuperior()) {
			funcionario.setChefe(true);
			this.chefe = funcionario;
			funcionario.setChefe(true);
			return true;
		}
		return false;
	}

	public double getPercentualComissao() {
		return percentualComissao;
	}

	public void setPercentualComissao(double percentualComissao) {
		this.percentualComissao = percentualComissao;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
}
