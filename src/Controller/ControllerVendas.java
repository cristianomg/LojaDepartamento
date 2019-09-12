package Controller;

import java.util.Scanner;

import Exceptions.ClienteNaoEncontradoException;
import Exceptions.ProdutoNaoEncontradoException;
import Exceptions.QuantidadeInsuficienteException;
import Exceptions.VendaEncerradaExpcetion;
import Exceptions.VendaNaoEncontradaException;
import Model.DAO.ClienteDAO;
import Model.DAO.ProdutoDAO;
import Model.DAO.VendaDAO;
import Model.Entites.Cliente;
import Model.Entites.Funcionario;
import Model.Entites.Produto;
import Model.Entites.Venda;
import View.VendaView;


public class ControllerVendas {
	private static ClienteDAO clientes = ClienteDAO.getInstance();
	private static VendaDAO vendas = VendaDAO.getInstance();
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	Scanner sc = new Scanner(System.in);
	private Funcionario funcionario;
	
	public ControllerVendas(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void abrirVender() {
		try {
			Cliente cliente =  clientes.getCliente("00000000000"); // criar a view
			Venda venda = new Venda(cliente, funcionario);
			vendas.inserir(venda);
			System.out.println("Venda Aberta com sucesso!!!");
		} catch (ClienteNaoEncontradoException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	public void adicionarProduto() {
		Venda venda = null;
		boolean finalizarAddProdutos;
		finalizarAddProdutos = true;
		Venda vendaAntiga = null;
		try {
			venda = vendas.getVenda(0);
			vendaAntiga = venda;
			VendaView.listaProdutosDepartamento(produtos.getLista(), venda.getFuncionario().getDepartamento());
			while (finalizarAddProdutos) {
				System.out.println("id do produto [-1 para finalizar]: "); // criar view
				Produto produto = produtos.getProduto(0);
				if (produto.getDepartamento().equals(funcionario.getDepartamento())) {
					venda.adicionarProduto(produto, 1, 10); // criar view
					System.out.println(venda.getListaVendaProduto());
					finalizarAddProdutos = false;
					vendas.atualizar(vendaAntiga, venda);
				}
			}
		} catch (VendaNaoEncontradaException | ProdutoNaoEncontradoException | VendaEncerradaExpcetion | QuantidadeInsuficienteException e) {
			System.out.println(e.getMessage());
			finalizarAddProdutos = false;
		} // view para busca pela venda


		}

	public void removerProdutos(){
		Venda venda = null;
		try {
			venda = vendas.getVenda(0); // view para busca pela venda
			VendaView.listarProdutosVenda(venda.getListaVendaProduto());
			venda.removerProduto(0);
		}catch (VendaNaoEncontradaException | VendaEncerradaExpcetion e) {
			System.out.println(e.getMessage());
		}
		}

	public void calcularValorVenda() {
		Venda venda = null;
		try {
			venda = vendas.getVenda(0); // view para busca pela venda
			float precoTotal = venda.calcularPrecoFinal();
			System.out.println("Valor da venda até o momento: "+ precoTotal);
			
		} catch (VendaNaoEncontradaException e) {
			System.out.println(e.getMessage());
		}
	}

	public void finalizarVenda(){
		Venda venda = null;
		Venda vendaAntiga = null;

		try {
			venda = vendas.getVenda(0); // view para busca pela venda
			vendaAntiga = venda;
			venda.finalizarVenda();
			vendas.atualizar(vendaAntiga, venda);
		} catch (VendaNaoEncontradaException | VendaEncerradaExpcetion e) {
			System.out.println(e.getMessage());
		} 
	}
}

