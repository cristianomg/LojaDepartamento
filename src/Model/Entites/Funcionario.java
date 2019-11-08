package Model.Entites;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.VendaDAO;

public class Funcionario implements Serializable{
	private static final long serialVersionUID = -7844914350878174481L;
	private String nome;
	private String matricula;
	private String senha;
	private Departamento departamento;
	private boolean chefe;
	private boolean ensinoSuperior;
	private boolean desligado;
	private LocalDate dataDeChefia;
	
	public Funcionario(String nome) {
		this.nome = nome;
		this.desligado = false;
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

	public List<Venda> getListaVenda() {
		List<Venda> listaVenda = VendaDAO.getInstance().getLista();
		List<Venda> response = new ArrayList<Venda>();
		listaVenda.stream().filter(x -> x.getFuncionario().equals(this)).forEach(x -> response.add(x));
		return response;
	}

	public boolean isChefe()
	{
		return chefe;
	}

	public void setChefe(boolean chefe) {
		this.dataDeChefia = LocalDate.now();
		this.chefe = chefe;
	}

	public boolean isEnsinoSuperior() {
		return this.ensinoSuperior;
	}

	public void setEnsinoSuperior(boolean ensinoSuperior) {
		this.ensinoSuperior = ensinoSuperior;
	}

	public boolean isDesligado() {
		return this.desligado;
	}

	public void setDesligado(boolean desligado) {
		this.desligado = desligado;
	}

	public LocalDate getDataDeChefia() {
		return dataDeChefia;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if(!(obj instanceof Funcionario)) {
			return false;
		}
		if (this.getMatricula().equals(((Funcionario) obj).getMatricula()) ) {
			return true;
		}
		return false;
		
	}

	@Override
	public String toString() {
		return matricula;
	}
	
	
}
