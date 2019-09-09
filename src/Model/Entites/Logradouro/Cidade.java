package Model.Entites.Logradouro;

import java.util.ArrayList;

public class Cidade {
	private int id;
	private String nome; 
	private String sigla;
	private Estado estado;
	private ArrayList<Endereco> listaEndereco = new ArrayList<Endereco>();
	

	public Cidade(String nome, Estado estado) {
		this.nome = nome;
		this.sigla = nome.substring(0, 3);
		this.estado = estado;
	}
	
	public Cidade(int id, String nome, String sigla, Estado estado) {
		this(nome, estado);
		this.id = id;
		this.sigla = sigla;
	}
	
	public Cidade(int id, String nome, String sigla, Estado estado, ArrayList<Endereco> listaEndereco) {
		this(id, nome, sigla, estado);
		this.listaEndereco = listaEndereco;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public ArrayList<Endereco> getListaEndereco() {
		return listaEndereco;
	}

	public void setListaEndereco(ArrayList<Endereco> listaEndereco) {
		this.listaEndereco = listaEndereco;
	}
	
	public void addEnderecoLista(Endereco endereco) {
		boolean contem = false;
		for (Endereco e: this.listaEndereco) {
			if (e.getId() == endereco.getId()){
				contem = true;
			}
		}
		if (!contem) {
			this.listaEndereco.add(endereco);
		}
		
	}
	
	
}
