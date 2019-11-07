package Model.Entites;

import java.io.Serializable;
import java.util.ArrayList;

import Exceptions.ValidateCpfException;
import Model.Entites.Logradouro.Endereco;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 6061610663606490041L;
	private int id;
	private String nome;
	private String cpf_cnpj;
	private Endereco endereco;
	private int telefone;
	private ArrayList<Venda> listaCompras = new ArrayList<Venda>();
	
	
	public Cliente(String nome, int telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public Cliente(String nome, String cpf_cnpj, int telefone){
		this(nome, telefone);
		this.cpf_cnpj = cpf_cnpj;
		this.id = hashCode();
	}
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}
	public void setCpf_cnpj(String cpf_cnpj) throws ValidateCpfException {
		if (cpf_cnpj.length()==11) {
			this.cpf_cnpj = cpf_cnpj;
		}
		else {
			throw new ValidateCpfException("Cpf invalido, insira apenas numeros!!!");
		}
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public ArrayList<Venda> getListaCompras() {
		return listaCompras;
	}
	public void setListaCompras(ArrayList<Venda> listaCompras) {
		this.listaCompras = listaCompras;
	}
	public void addVenda(Venda venda) {
		this.listaCompras.add(venda);
	}
	@Override
	public String toString() {
		return nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf_cnpj == null) ? 0 : cpf_cnpj.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf_cnpj == null) {
			if (other.cpf_cnpj != null)
				return false;
		} else if (!cpf_cnpj.equals(other.cpf_cnpj))
			return false;
		return true;
	}



	
}
