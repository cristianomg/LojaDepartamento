package Model.Entites;

public class VendaProduto {
	private Venda venda;
	private Produto produto;
	private float preco;
	private int quantidade;
	
	public VendaProduto(Venda venda, Produto produto, int quantidade, float desconto) {
		super();
		float descontoFinal = 1-desconto;
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.preco =(produto.getPreco() * quantidade * descontoFinal); 
	}
	
	public Venda getVenda() {
		return venda;
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
