package Controller;

import java.util.HashMap;
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
	private VendaView vendaView = new VendaView();
	
	public ControllerVendas(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void abrirVender() {
		try {
			String cpfCliente = vendaView.requestAbrirVenda();
			Cliente cliente =  clientes.getCliente(cpfCliente);
			int codigo = vendas.getLista().size();
			Venda venda = new Venda(codigo, cliente, funcionario);
			vendas.inserir(venda);
			System.out.println("O codigo da venda é "+ venda.getCodigo());
			System.out.println("Venda Aberta com sucesso.");
		} catch (ClienteNaoEncontradoException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	public void adicionarProduto() {
		Venda venda = null;
		boolean finalizarAddProdutos;
		finalizarAddProdutos = true;
		try {
			int codigoVenda = vendaView.requestBuscaPorVenda();
			venda = vendas.getVenda(codigoVenda);
			VendaView.listaProdutosDepartamento(produtos.getLista(), funcionario.getDepartamento());
			while (finalizarAddProdutos){
				HashMap<String, Integer> solicitarProduto = vendaView.adicionarProdutoVenda();
				int idProduto = solicitarProduto.get("idProduto");
				int quantidade = solicitarProduto.get("quantidade");
				int desconto = solicitarProduto.get("desconto");
				if (idProduto != -1) {
					Produto produto = produtos.getProduto(idProduto);
					if (produto.getDepartamento().equals(funcionario.getDepartamento())) {
						venda.adicionarProduto(produto, quantidade, desconto);
						System.out.println(venda.getListaVendaProduto());
						finalizarAddProdutos = false;
						vendas.atualizar(venda);
					}
					else {
						System.out.println("Erro: Esse produto não pertence ao departamento.");
					}
				}
				else {
					finalizarAddProdutos = false;
				}
			}
		} catch (VendaNaoEncontradaException | ProdutoNaoEncontradoException | VendaEncerradaExpcetion | QuantidadeInsuficienteException e) {
			System.out.println(e.getMessage());
			finalizarAddProdutos = false;
		}


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
		try {
			venda = vendas.getVenda(0); // view para busca pela venda
			venda.finalizarVenda();
			vendas.atualizar(venda);
		} catch (VendaNaoEncontradaException | VendaEncerradaExpcetion e) {
			System.out.println(e.getMessage());
		} 
	}
}

