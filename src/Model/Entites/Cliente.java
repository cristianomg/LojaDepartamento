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
	private String telefone;
	private ArrayList<Venda> listaCompras = new ArrayList<Venda>();
	
	
	public Cliente(int id) {
		this.id = id;
	}
	public Cliente(int id, String nome) {
		this(id);
		this.nome = nome;
	}
	
	public Cliente(int id, String nome, String cpf_cnpj) throws ValidateCpfException{
		this(id, nome);
		if (cpf_cnpj.length()==11) {
			this.cpf_cnpj = cpf_cnpj;
		}
		else {
			throw new ValidateCpfException("Cpf invalido, insira apenas numeros!!!");
		}
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
