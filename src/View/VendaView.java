package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Departamento;
import Model.Entites.Produto;
import Model.Entites.ProdutoEletronico;
import Model.Entites.Venda;
import Model.Entites.VendaProduto;

public class VendaView {
	private Scanner sc;

	public static void listaProdutosDepartamento(List<Produto> produtos, Departamento departamento) {
		System.out.println("Lista de Produtos do departamento: ");
		System.out.println("-".repeat(58));
		System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s", "ID", " ", "Nome do Produto", " ", "Preço", " ",
				"Quantidade", " ", "Prod Marca");
		System.out.println();
		System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s", "-".repeat(4), " ", "-".repeat(30), " ",
				"-".repeat(10), " ", "-".repeat(10), " ", "-".repeat(10));
		System.out.println();
		for (Produto produto : produtos) {
			if (produto.getDepartamento().equals(departamento)) {
				if (produto instanceof ProdutoEletronico) {
					ProdutoEletronico p = (ProdutoEletronico) produto;
					System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s", produto.getId(), " ", produto.getNome(),
							" ", produto.getPreco(), " ", produto.getQuantidade(), " ", p.ehProdutoMarca());

					System.out.println();
					System.out.println();
					System.out.println("Descrição: " + produto.getDescricao());
					System.out.println("-".repeat(58));
				} else {
					System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s", produto.getId(), " ", produto.getNome(),
							" ", produto.getPreco(), " ", produto.getQuantidade(), " ", false);

					System.out.println();
					System.out.println();
					System.out.println("Descrição: " + produto.getDescricao());
					System.out.println("-".repeat(58));
				}
			}
		}
	}

	public static void listarProdutosVenda(List<VendaProduto> vendaProdutos) {
		System.out.println("Produtos adicionados na venda: ");
		System.out.printf("%-58s", "---------------------------------------------------------------------");
		System.out.println();
		System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s", "ID", " ", "Nome do Produto", " ", "Preço", " ",
				"Quantidade", " ", "Sub Total");
		System.out.println();
		System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s", "----", " ", "--------------------------", " ",
				"--------", " ", "----------", " ", "----------");
		System.out.println();
		if (vendaProdutos.size() >= 1) {
			for (VendaProduto vendaProduto : vendaProdutos) {
				Produto produto = vendaProduto.getProduto();
				System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s", produto.getId(), " ", produto.getNome(), " ",
						produto.getPreco(), " ", vendaProduto.getQuantidade(), " ", vendaProduto.getPreco());
				System.out.println();
				System.out.println();
				System.out.println("Descrição: " + produto.getDescricao());
				System.out.printf("%-58s", "---------------------------------------------------------------------");
				System.out.println();
			}
		} else {
			System.out.println("Nenhum produto adicionado na venda!!!");
		}
	}

	public static void listarVendas(List<Venda> vendas) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ", "X Y Z Comercio LTDA", "  ",
				"-----------------------");
		System.out.println();
		System.out.printf("%-20s%s%-20s%s%-18s", "----------------------", "  ", "Listagem de Vendas", "  ",
				"-----------------------");
		System.out.println();
		System.out.println();
		if (!vendas.isEmpty()) {
			for (Venda venda : vendas) {
				if (venda.isVendaFinalizada()) {
					System.out.printf("%-6s%-1s%-28s%-1s%-10s%-1s%-10s%-1s%s", "Codigo", " ", "Funcionario", " ",
							"Departamento", " ", "Cliente", " ", "Data");
					System.out.println();
					System.out.printf("%-6s%-1s%-28s%-1s%-10s%-1s%-10s%-1s%s", "------", " ",
							"--------------------------", " ", "------------", " ", "----------", " ", "---------");
					System.out.println();
					System.out.printf("%-6s%-1s%-28s%-1s%-10s%-1s%-10s%-1s%s", venda.getCodigo(), " ",
							venda.getFuncionario().getMatricula(), " ",
							venda.getFuncionario().getDepartamento().getNome(), " ", venda.getCliente().getNome(), " ",
							venda.getData());
					System.out.println();
					System.out.println();
					ArrayList<VendaProduto> vendaProdutos = venda.getListaVendaProduto();
					listarProdutosVenda(vendaProdutos);
					System.out.println("Preço total: " + venda.getPrecoTotal());
					System.out.println();
					System.out.println();
				}
			}
		}else {
			System.out.println("Nenhuma venda cadastrada.");
		}
	}

	public int requestBuscaPorVenda() {
		sc = new Scanner(System.in);
		System.out.print("Infome o codigo da venda: ");
		int codigo = 0;
		boolean inputConcluido = false;
		do {
			try {
				codigo = Integer.parseInt(sc.nextLine());
				inputConcluido = true;
			} catch (NumberFormatException e) {
				System.out.println("Erro: Informações invalida, tente novamente");
				sc.next();
			}
		} while (!inputConcluido);
		return codigo;

	}

	public static void responseBuscaPorVenda(Venda venda) {
		System.out.printf("%-6s%-1s%-28s%-1s%-10s%-1s%-10s%-1s%s", "Codigo", " ", "Funcionario", " ", "Departamento",
				" ", "Cliente", " ", "Data");
		System.out.println();
		System.out.printf("%-6s%-1s%-28s%-1s%-10s%-1s%-10s%-1s%s", "------", " ", "--------------------------", " ",
				"------------", " ", "----------", " ", "---------");
		System.out.println();
		System.out.printf("%-6s%-1s%-28s%-1s%-10s%-1s%-10s%-1s%s", venda.getCodigo(), " ",
				venda.getFuncionario().getMatricula(), " ", venda.getFuncionario().getDepartamento().getNome(), " ",
				venda.getCliente().getNome(), " ", venda.getData());
		System.out.println();
		System.out.println();
		ArrayList<VendaProduto> vendaProdutos = venda.getListaVendaProduto();
		listarProdutosVenda(vendaProdutos);
		System.out.println("Preço total: " + venda.getPrecoTotal());
	}

	public String requestAbrirVenda() {
		sc = new Scanner(System.in);
		System.out.print("Informe o cpf ou cnpj do cliente: ");
		String cpf_cnpj;
		boolean inputConcluido = false;
		do {
			cpf_cnpj = sc.nextLine();
			if (cpf_cnpj.length() == 11) {
				inputConcluido = true;
			} else {
				System.out.print("Entrada invalida, tente novamente: ");
			}
		} while (!inputConcluido);
		return cpf_cnpj;

	}

	public HashMap<String, Integer> adicionarProdutoVenda() {
		sc = new Scanner(System.in);
		HashMap<String, Integer> response = new HashMap<String, Integer>();
		boolean inputConcluido = false;
		do {
			try {
				System.out.print("Informe o Id do produto: [digite -1 para finalizar] ");
				Integer idProduto = sc.nextInt();
				response.put("idProduto", idProduto);
				if (idProduto == -1) {
					break;
				} else {
					System.out.print("Informe a quantidade: ");
					Integer quantidade = sc.nextInt();
					response.put("quantidade", quantidade);
					System.out.print("Informe o desconto: ");
					Integer desconto = sc.nextInt();
					response.put("desconto", desconto);
					inputConcluido = true;
				}
			} catch (InputMismatchException | NullPointerException e) {
				System.out.println("Valor invalido, tente novamente.");
				sc.next();
			}
		} while (!inputConcluido);
		return response;
	}

	public int solicitarIdProdutoRemover() {
		sc = new Scanner(System.in);
		boolean inputConcluido = false;
		int idProduto = 0;
		do {
			try {
				System.out.println("Infome o id do produto a ser removido: ");
				idProduto = sc.nextInt();
				inputConcluido = true;
			} catch (InputMismatchException e) {
				System.out.print("Erro: Informação invalida, tente novamente: ");
			}
		} while (!inputConcluido);
		return idProduto;
	}
}
