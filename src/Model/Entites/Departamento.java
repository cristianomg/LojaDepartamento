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

	public Departamento(int id) {
		this.id = id;
		this.percentualComissao = 3;
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
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Departamento)) {
			return false;
		}
		if (this.getId() == ((Departamento) obj).getId()) {
			return true;
		}
		return false;

	}
}
