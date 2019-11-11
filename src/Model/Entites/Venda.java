package Model.Entites;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import Model.DAO.IdsDAO;

public class Venda implements Serializable {
	private static final long serialVersionUID = 5197014291763156867L;
	private int codigo;
	private LocalDate data;
	private float precoTotal;
	private Cliente cliente;
	private Funcionario funcionario;
	private Departamento departamento;
	private List<VendaProduto> listaVendaProduto;
	private boolean vendaFinalizada;
	private double comissaoFuncionario;
	
		
	public Venda(Cliente cliente, Funcionario funcionario) {
		this.codigo = gerarId();
		IdsDAO.getInstance().save();
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.departamento = funcionario.getDepartamento();
	}
	public Venda(LocalDate data, float precoTotal, Cliente cliente, Funcionario funcionario,
			List<VendaProduto> listaVendaProduto) {
		this(cliente, funcionario);
		this.data = data;
		this.precoTotal = precoTotal;
		this.listaVendaProduto = listaVendaProduto;
	}
	
	public int getCodigo() {
		return codigo;
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
	public List<VendaProduto> getListaVendaProduto() {
		return listaVendaProduto;
	}
	public void setListaVendaProduto(List<VendaProduto> listaVendaProduto) {
		this.listaVendaProduto = listaVendaProduto;
	}
	
	public boolean isVendaFinalizada() {
		return vendaFinalizada;
	}
	public float calcularPrecoFinal() {
		precoTotal = 0;
		for(VendaProduto vp: this.listaVendaProduto) {
			precoTotal += vp.getSubTotal();
		}
		return precoTotal;
	}
	public void finalizarVenda(){
			this.precoTotal = this.calcularPrecoFinal();
			this.calcularComissao();
			this.data = LocalDate.now();
			vendaFinalizada = true;
	}
	
	private void calcularComissao() {
		float precoFinal = this.precoTotal;
		this.comissaoFuncionario = (precoFinal * this.funcionario.getDepartamento().getPercentualComissao())/100;
		
	}
	public double getComissaoFuncionario() {
		return comissaoFuncionario;
	}
	//duvida
	public List<Produto> obterProdutosSimilares(){
		return null;
	}
	//duvida
	public List<Produto> obterProdutosDestaques(){
		return null;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	private int gerarId() {
		HashSet<Integer> listaIds = IdsDAO.getInstance().getIdsVendas();
		Random rand = new Random();
		int id = rand.nextInt((10000 - 0) + 1) + 0;
		return listaIds.add(id) ? id :  gerarId();
	}
}
