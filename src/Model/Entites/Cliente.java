package Model.Entites;

import java.util.ArrayList;

import Model.Entites.Logradouro.Endereco;

public class Cliente {
	private String nome;
	private String cpf_cnpj;
	private Endereco endereco;
	private String telefone;
	private ArrayList<Venda> listaCompras = new ArrayList<Venda>();
	
	
	public Cliente() {
	}
	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public Cliente(String nome, String cpf_cnpj) {
		this(nome);
		this.cpf_cnpj = cpf_cnpj;
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
	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
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
}
