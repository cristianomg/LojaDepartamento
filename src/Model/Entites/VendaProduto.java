package Model.Entites;

import java.io.Serializable;

public class VendaProduto implements Serializable {
	private static final long serialVersionUID = -3472689279816084286L;
	private Venda venda;
	private Produto produto;
	private float preco;
	private int quantidade;
	private float desconto;
	
	public VendaProduto(Venda venda, Produto produto, int quantidade, float desconto) {
		super();
		this.desconto = (100-desconto)/100;

		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.preco = (produto.getPreco() * quantidade * this.desconto); 
	}
	
	public Venda getVenda() {
		return venda;
	}
	public float getDesconto() {
		return desconto;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getPreco() {
		return preco;
	}
	public Cliente getCliente() {
		return venda.getCliente();
	}
	public Funcionario getFuncionario() {
		return venda.getFuncionario();
	}
	
	

}
