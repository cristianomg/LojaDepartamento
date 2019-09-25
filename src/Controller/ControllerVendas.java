package Controller;

import java.util.HashMap;
import java.util.Scanner;

import Exceptions.ClienteNaoEncontradoException;
import Exceptions.ProdutoNaoEncontradoException;
import Exceptions.QuantidadeInsuficienteException;
import Exceptions.VendaEncerradaExpcetion;
import Exceptions.VendaNaoEncontradaException;
import Model.DAO.ClienteDAO;
import Model.DAO.FuncionarioDAO;
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
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	Scanner sc = new Scanner(System.in);
	private Funcionario funcionario;
	private VendaView vendaView = new VendaView();
	
	public ControllerVendas(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void abrirVender(String cpfCliente) {
		try {
			Cliente cliente =  clientes.getCliente(cpfCliente);
			int codigo = vendas.getLista().size();
			Venda venda = new Venda(codigo, cliente, funcionario);
			vendas.inserir(venda);
			System.out.println("O codigo da venda é "+ venda.getCodigo());
			System.out.println("Venda Aberta com sucesso.");
		} catch (ClienteNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void adicionarProduto(int codigoVenda) {
		Venda venda = null;
		boolean finalizarAddProdutos;
		finalizarAddProdutos = true;
		try {
			venda = vendas.getVenda(codigoVenda);
			if (venda.getFuncionario().equals(funcionario)) {
				VendaView.listaProdutosDepartamento(produtos.getLista(), funcionario.getDepartamento());
				while (finalizarAddProdutos){
					HashMap<String, Integer> solicitarProduto = vendaView.adicionarProdutoVenda();
					int idProduto = solicitarProduto.get("idProduto");
					if (idProduto != -1) {
						int quantidade = solicitarProduto.get("quantidade");
						int desconto = solicitarProduto.get("desconto");
						Produto produto = produtos.getProduto(idProduto);
						if (produto.getDepartamento().equals(funcionario.getDepartamento())) {
							venda.adicionarProduto(produto, quantidade, desconto);
							System.out.println("Produto Adicionado.");
							vendas.atualizar(venda);
						}else {
							System.out.println("Erro: Esse produto não pertence ao departamento.");
						}
					}else {
						finalizarAddProdutos = false;
					}
				}
			}else {
				System.out.println("Erro: Essa venda pertence ao funcionario " + venda.getFuncionario().getNome() + "contate o mesmo.");
			}
		}catch (VendaNaoEncontradaException | ProdutoNaoEncontradoException | VendaEncerradaExpcetion | QuantidadeInsuficienteException e) {
			System.out.println(e.getMessage());
			finalizarAddProdutos = false;
		}
	}

	public void removerProdutos(int codigoVenda){
		Venda venda = null;
		try {
			venda = vendas.getVenda(codigoVenda);
			if(venda.getFuncionario().equals(funcionario)) {
				VendaView.listarProdutosVenda(venda.getListaVendaProduto());
				int idProduto = vendaView.solicitarIdProdutoRemover();
				venda.removerProduto(idProduto); 
				System.out.println("Produto removido com sucesso.");
				vendas.atualizar(venda);
			}else {
				System.out.println("Erro: Essa venda pertence ao funcionario " + venda.getFuncionario().getNome() + " contate o mesmo.");
			}

		}catch (VendaNaoEncontradaException | VendaEncerradaExpcetion e) {
			System.out.println(e.getMessage());
		}
		}

	public void calcularValorVenda(int codigoVenda) {
		Venda venda = null;
		try {
			venda = vendas.getVenda(codigoVenda);
			if (venda.getFuncionario().equals(funcionario)) {
				float precoTotal = venda.calcularPrecoFinal();
				System.out.println("Valor da venda até o momento: "+ precoTotal);
			}else {
				System.out.println("Erro: Essa venda pertence ao funcionario " + venda.getFuncionario().getNome() + " contate o mesmo.");
			}

			
		} catch (VendaNaoEncontradaException e) {
			System.out.println(e.getMessage());
		}
	}

	public void finalizarVenda(int codigoVenda){
		Venda venda = null;
		try {
			venda = vendas.getVenda(codigoVenda); 
			if(venda.getFuncionario().equals(funcionario)) {
				venda.finalizarVenda();
				funcionario.adicionarVenda(venda);
				vendas.atualizar(venda);
				funcionarios.atualizar(funcionario);
				System.out.println("Venda finalizada com sucesso.");
			}else {
				System.out.println("Erro: Essa venda pertence ao funcionario " + venda.getFuncionario().getNome() + "contate o mesmo.");
			}
		} catch (VendaNaoEncontradaException | VendaEncerradaExpcetion e) {
			System.out.println(e.getMessage());
		} 
	}
}

