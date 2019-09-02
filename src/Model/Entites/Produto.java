package Model.Entites;

import java.util.ArrayList;

import Model.Entites.Logradouro.Cidade;

public class Produto {
	private int id;
	private String nome;
	private String descricao;
	private float preco;
	private int quantidade;
	private ArrayList<Produto> listaSimilar = new ArrayList<Produto>();
	private Produto produtoOriginal;
	private ArrayList<VendaProduto> listaVendaProduto = new ArrayList<VendaProduto>();
	
	public Produto(int id, String nome, String descricao, float preco, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.produtoOriginal = null;
	}
	public Produto(int id, String nome, String descricao, float preco, int quantidade, ArrayList<Produto> listaSimilar,
			Produto produtoOriginal, ArrayList<VendaProduto> listaVendaProduto) {
		this(id, nome, descricao, preco, quantidade);
		this.listaSimilar = listaSimilar;
		this.produtoOriginal = produtoOriginal;
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
	public ArrayList<Produto> getListaSimilar() {
		return listaSimilar;
	}
	public void setListaSimilar(ArrayList<Produto> listaSimilar) {
		this.listaSimilar = listaSimilar;
	}
	public Produto getProdutoOriginal() {
		return produtoOriginal;
	}
	public void setProdutoOriginal(Produto produtoOriginal) {
		this.produtoOriginal = produtoOriginal;
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
	public void cadastrarSimilar(Produto produto) {
		if(produto.getProdutoOriginal().getId() == this.id) {
			this.listaSimilar.add(produto);
		}
	}
	public void excluirSmiliar(Produto produto) {
		this.listaSimilar.remove(produto);
		produto.setProdutoOriginal(null);
	}
	public boolean ehProdutoMarca() {
		if(this.produtoOriginal == null) {
			return true;
		}
		else {
			return false;
		}
	}
	//duvida
	public void ehProdutoSimilar() {
		
	}
	
}
