package Model.Entites;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Exceptions.QuantidadeInsuficienteException;
import Exceptions.VendaEncerradaExpcetion;
import Exceptions.VendaNaoEncontradaException;

public class Venda implements Serializable {
	private static final long serialVersionUID = 5197014291763156867L;
	private int codigo;
	private LocalDate data;
	private float precoTotal;
	private Cliente cliente;
	private Funcionario funcionario;
	private Departamento departamento;
	private ArrayList<VendaProduto> listaVendaProduto = new ArrayList<VendaProduto>();
	private boolean vendaFinalizada;
	private double comissaoFuncionario;
		
	public Venda(int codigo, Cliente cliente, Funcionario funcionario) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.departamento = funcionario.getDepartamento();
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
	
	public boolean isVendaFinalizada() {
		return vendaFinalizada;
	}
	public void setVendaFinalizada(boolean vendaFinalizada) {
		this.vendaFinalizada = vendaFinalizada;
	}
	public float calcularPrecoFinal() {
		precoTotal = 0;
		for(VendaProduto vp: this.listaVendaProduto) {
			precoTotal += vp.getSubTotal();
		}
		return precoTotal;
	}
	public void finalizarVenda() throws VendaEncerradaExpcetion {
		if(!vendaFinalizada) {
			this.precoTotal = this.calcularPrecoFinal();
			this.calcularComissao();
			this.data = LocalDate.now();
			vendaFinalizada = true;
		}
		else {
			throw new VendaEncerradaExpcetion("Venda encerrada para modifica-la reabra!!!");
		}
	}
	private void calcularComissao() {
		float precoFinal = this.precoTotal;
		this.comissaoFuncionario = (precoFinal * this.funcionario.getDepartamento().getPercentualComissao())/100;
		
	}
	public double getComissaoFuncionario() {
		return comissaoFuncionario;
	}
	public void removerProduto(int produtoId) throws VendaNaoEncontradaException, VendaEncerradaExpcetion{
		if (!vendaFinalizada) {
			boolean contem = false;
			for(VendaProduto vp: this.listaVendaProduto) {
				if (vp.getProduto().getId() == produtoId) {
					vp.getProduto().addQuantidade(vp.getQuantidade());
					this.listaVendaProduto.remove(vp);
					contem = true;
					break;
				}
			}
			if (!contem) {
				throw new VendaNaoEncontradaException("Produto nao encontrado!");
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
	public void adicionarProduto(Produto produto, int quantidade, int desconto) throws VendaEncerradaExpcetion, QuantidadeInsuficienteException {
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
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
