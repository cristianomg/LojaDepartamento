package Controller;

import java.util.Scanner;

import Exceptions.ObjetoNaoEncontradoException;
import Exceptions.QuantidadeInsuficienteException;
import Exceptions.VendaEncerradaExpcetion;
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
			Cliente cliente =  clientes.getCliente("05897257507"); // criar a view
			Venda venda = new Venda(cliente, funcionario);
			vendas.inserir(venda);
			System.out.println("Venda Aberta com sucesso!!!");
		} catch (ObjetoNaoEncontradoException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	public void adicionarProduto() {
		Venda venda = null;
		boolean finalizarAddProdutos;
		finalizarAddProdutos = true;
		try {
			venda = vendas.getVenda(0); // view para busca pela venda
			VendaView.listaProdutosDepartamento(produtos.getLista(), venda.getFuncionario().getDepartamento());
			while (finalizarAddProdutos) {
				System.out.println("id do produto [-1 para finalizar]: "); // criar view
				Produto produto = produtos.getProduto(0);
				if (produto.getDepartamento().equals(funcionario.getDepartamento())) {
					try {
						venda.adicionarProduto(produto, 100, 10); // criar view
						System.out.println(venda.getListaVendaProduto());
						finalizarAddProdutos = false;
					} catch (VendaEncerradaExpcetion | QuantidadeInsuficienteException e) {
						System.out.println(e.getMessage());
						finalizarAddProdutos = false;
					} 
				}
				else {
					System.out.println("O produto n�o pertence a esse departamento tente com outro vendedor!!!");
				}
			}
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println(e.getMessage());
			finalizarAddProdutos = true;
			e.printStackTrace();
		}
		}

	public void removerProdutos() {
		// TODO Auto-generated method stub
		
	}

	public void calcularValorVenda() {
		// TODO Auto-generated method stub
		
	}

	public void finalizarVenda() {
		// TODO Auto-generated method stub
		
	}
	}

