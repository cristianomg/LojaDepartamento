package Model.Entites.Logradouro;

import java.util.ArrayList;

public class Estado {
	private static int contador;
	private int id;
	private String nome;
	private String sigla;
	private ArrayList<Cidade> listaCidade = new ArrayList<Cidade>();
	
	public Estado(String nome) {
		this.id = contador;
		this.nome = nome;
		this.sigla = nome.substring(0, 3);
		contador ++;
	}
	public Estado(int id, String nome) {
		this(nome);
		this.id = id;
	}
	
	public Estado(int id, String nome, String sigla) {
		this(id, nome);
		this.sigla = sigla;
	}

	public Estado(int id, String nome, String sigla, ArrayList<Cidade> listaCidade) {
		this(id, nome, sigla);
		this.listaCidade = listaCidade;
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

	public ArrayList<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(ArrayList<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public void addCidadeLista(Cidade cidade) {
		boolean contem = false;
		for(Cidade c: this.listaCidade) {
			if(cidade.getId() == c.getId()) {
				contem = true;
			}
		}
		if (!contem) {
			this.listaCidade.add(cidade);
		}
	}



	
	
}
