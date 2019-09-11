package Model.Entites;

import java.time.LocalDate;
import java.util.ArrayList;

import Exceptions.ObjetoNaoEncontradoException;
import Exceptions.QuantidadeInsuficienteException;
import Exceptions.VendaEncerradaExpcetion;

public class Venda {
	private static int id;
	private int codigo;
	private LocalDate data;
	private float precoTotal;
	private Cliente cliente;
	private Funcionario funcionario;
	private ArrayList<VendaProduto> listaVendaProduto = new ArrayList<VendaProduto>();
	private boolean vendaFinalizada;
		
	public Venda(Cliente cliente, Funcionario funcionario) {
		this.codigo = id;
		this.cliente = cliente;
		this.funcionario = funcionario;
		id ++;
	}
	public Venda(int codigo, LocalDate data, float precoTotal, Cliente cliente, Funcionario funcionario,
			ArrayList<VendaProduto> listaVendaProduto) {
		this(cliente, funcionario);
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
	
	public boolean isVendaFinalizada() {
		return vendaFinalizada;
	}
	public void setVendaFinalizada(boolean vendaFinalizada) {
		this.vendaFinalizada = vendaFinalizada;
	}
	public float calcularPrecoFinal() {
		precoTotal = 0;
		for(VendaProduto vp: this.listaVendaProduto) {
			precoTotal += vp.getPreco();
		}
		return precoTotal;
	}
	public void finalizarVenda() throws VendaEncerradaExpcetion {
		if(!vendaFinalizada) {
			this.precoTotal = this.calcularPrecoFinal();
			this.data = LocalDate.now();
			System.out.println(this.data);
			vendaFinalizada = true;
		}
		else {
			throw new VendaEncerradaExpcetion("Venda encerrada para modifica-la reabra!!!");
		}
	}
	public void removerProduto(int produtoId) throws ObjetoNaoEncontradoException, VendaEncerradaExpcetion{
		if (!vendaFinalizada) {
			boolean contem = false;
			for(VendaProduto vp: this.listaVendaProduto) {
				if (vp.getProduto().getId() == produtoId) {
					vp.getProduto().addQuantidade(vp.getQuantidade());
					this.listaVendaProduto.remove(vp);
					break;
				}
			}
			if (!contem) {
				throw new ObjetoNaoEncontradoException("Produto nao encontrado!");
			}
		}
		else {
			throw new VendaEncerradaExpcetion("Venda encerrada para modifica-la reabra!!!");
		}
	}
	public void removerProduto(VendaProduto produto) throws VendaEncerradaExpcetion {
		if(!vendaFinalizada) {
			for (VendaProduto vp:this.listaVendaProduto) {
				if (vp.equals(produto)){
					vp.getProduto().addQuantidade(vp.getQuantidade());
					this.listaVendaProduto.remove(produto);
					break;
				}
			}
		}
		else {
			throw new VendaEncerradaExpcetion("Venda encerrada para modifica-la reabra!!!");
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
	public void adicionarProduto(Produto produto, int quantidade, float desconto) throws VendaEncerradaExpcetion, QuantidadeInsuficienteException {
		if (!vendaFinalizada) {
			if (produto.getQuantidade() >= quantidade) {
				VendaProduto vendaProduto = new VendaProduto(this, produto, quantidade, desconto);
				produto.vender(quantidade, vendaProduto);
				this.listaVendaProduto.add(vendaProduto);
			}
			else {
				throw new QuantidadeInsuficienteException("Quantidade insuficiente no estoque");
			}

		}
		else {
			throw new VendaEncerradaExpcetion("Venda encerrada para modifica-la reabra!!!");
		}
	}
	
}
