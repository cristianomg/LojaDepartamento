package Model.Entites;

import java.io.Serializable;
import java.util.ArrayList;

import Model.Entites.Logradouro.Cidade;

public class Produto implements Serializable{
	protected static final long serialVersionUID = -1078825280829539490L;
	protected int id;
	protected String nome;
	protected String descricao;
	protected float preco;
	protected int quantidade;
	protected ArrayList<VendaProduto> listaVendaProduto = new ArrayList<VendaProduto>();
	protected Departamento departamento;
	
	public Produto(int id, String nome, String descricao, float preco, int quantidade, Departamento departamento) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.departamento = departamento;
	}
	public Produto(int id, String nome, String descricao, float preco, int quantidade, Departamento departamento, ArrayList<VendaProduto> listaVendaProduto) {
		this(id, nome, descricao, preco, quantidade, departamento);
		this.listaVendaProduto = listaVendaProduto;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public ArrayList<VendaProduto> getListaVendaProduto() {
		return listaVendaProduto;
	}
	public void setListaVendaProduto(ArrayList<VendaProduto> listaVendaProduto) {
		this.listaVendaProduto = listaVendaProduto;
	}
	
	public int getQuantidadeVendas() {
		return listaVendaProduto.size();
	}
	public float getTotalArrecadadoVenda() {
		float totalArrecadadoVenda = 0;
		for(VendaProduto vp: this.listaVendaProduto) {
			totalArrecadadoVenda += vp.getPreco();
		}
		return totalArrecadadoVenda;
		
	}
	public ArrayList<Cidade> getListaCidadeClientes(){
		ArrayList<Cidade> cidadesClientes = new ArrayList<Cidade>();
		for(VendaProduto vp: listaVendaProduto) {
			Cidade cidade = vp.getCliente().getEndereco().getCidade();
			cidadesClientes.add(cidade);
		}
		return cidadesClientes;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public void vender(int quantidade, VendaProduto vendaProduto) {
		this.quantidade -= quantidade;
		this.listaVendaProduto.add(vendaProduto);
	}
	
	public void addQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}
	
}
