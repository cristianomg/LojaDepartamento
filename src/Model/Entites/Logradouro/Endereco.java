package Model.Entites.Logradouro;

import java.io.Serializable;

public class Endereco implements Serializable{
	private static final long serialVersionUID = -2015754334260585417L;
	private static int contador;
	private int id;
	private String rua;
	private int numero;
	private String bairro;
	private String cep;
	private Cidade cidade;
	private Estado estado;
	
	public Endereco(String rua, int numero, String bairro, String cep, Cidade cidade, Estado estado) {
		this.id = contador;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		contador ++;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Endereco(int id, String rua, int numero, String bairro, String cep, Cidade cidade, Estado estado) {
		this(rua, numero, bairro, cep, cidade, estado);
		this.id = id;

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
}
