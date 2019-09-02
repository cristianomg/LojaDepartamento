package Model.Entites;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venda {
	private int codigo;
	private LocalDate data;
	private float precoTotal;
	private Cliente cliente;
	private Funcionario funcionario;
	private ArrayList<VendaProduto> listaVendaProduto = new ArrayList<VendaProduto>();
	private boolean statusVenda;
	
	
	
	public Venda(int codigo, Cliente cliente, Funcionario funcionario) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.funcionario = funcionario;
	}
	public Venda(int codigo, LocalDate data, float precoTotal, Cliente cliente, Funcionario funcionario,
			ArrayList<VendaProduto> listaVendaProduto) {
		this(codigo, cliente, funcionario);
		this.data = data;
		this.precoTotal = precoTotal;
		this.listaVendaProduto = listaVendaProduto;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public float getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public ArrayList<VendaProduto> getListaVendaProduto() {
		return listaVendaProduto;
	}
	public void setListaVendaProduto(ArrayList<VendaProduto> listaVendaProduto) {
		this.listaVendaProduto = listaVendaProduto;
	}
	
	public boolean isStatusVenda() {
		return statusVenda;
	}
	public void setStatusVenda(boolean statusVenda) {
		this.statusVenda = statusVenda;
	}
	private float calcularPrecoFinal() {
		precoTotal = 0;
		for(VendaProduto vp: this.listaVendaProduto) {
			precoTotal += vp.getPreco();
		}
		return precoTotal;
	}
	public void finalizarVenda() {
		if(!statusVenda) {
			this.precoTotal = this.calcularPrecoFinal();
			statusVenda = true;
		}
	}
	public void removerProduto(int produtoId) {
		if (!statusVenda) {
			boolean contem = false;
			for(VendaProduto vp: this.listaVendaProduto) {
				if (vp.getProduto().getId() == produtoId) {
					this.listaVendaProduto.remove(vp);
				}
			}
			if (!contem) {
				System.out.println("Produto não encontrado!!!");
			}
		}
	}
	public void removerProduto(VendaProduto produto) {
		if(!statusVenda) {
			for (VendaProduto vp:this.listaVendaProduto) {
				if (vp.equals(produto)){
					this.listaVendaProduto.remove(produto);
				}
			}
		}
	}
	//duvida
	public ArrayList<Produto> obterProdutosSimilares(){
		return null;
	}
	//duvida
	public ArrayList<Produto> obterProdutosDestaques(){
		return null;
	}
	//duvida
	public void adicionarProduto(Produto produto, int quantidade, float desconto) {
	}
	
}
