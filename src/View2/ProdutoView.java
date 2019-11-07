package View2;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Produto;
import Model.Entites.ProdutoEletronico;

public class ProdutoView {
	private Scanner sc = new Scanner(System.in);

	@SuppressWarnings("unused")
	public HashMap<String, String> cadastrarProduto() {
		System.out.printf("%-18s%s%-20s%s%-18s", "--------------------", "  ", "Cadastro de Produtos", " ",
				"---------------------");
		System.out.println();
		HashMap<String, String> response = new HashMap<String, String>();
		boolean inputConcluido = false;
		do {
			try {
				System.out.print("Informe o nome do produto: ");
				String nome = sc.nextLine();
				System.out.print("Informe a Preço unitario do produto: ");
				String preco = sc.nextLine();
				float precoTest = Float.parseFloat(preco);
				System.out.print("Informe a descrição do produto: ");
				String descricao = sc.nextLine();
				System.out.print("Informe o Id do departamento que o produto será vendido: ");
				String idDepartamento = sc.nextLine();
				int idDepartamentoTest = Integer.parseInt(idDepartamento);
				if (nome.length() > 0 && descricao.length() > 0) {
					response.put("nome", nome);
					response.put("preco", preco);
					response.put("descricao", descricao);
					response.put("idDepartamento", idDepartamento);
					inputConcluido = true;
				} else {
					System.out.println("Erro: Informações invalida, tente novamente");
				}
			} catch (NumberFormatException e) {
				System.out.println("Erro: Informações invalida, tente novamente");
			}
		} while (!inputConcluido);
		return response;
	}

	@SuppressWarnings("unused")
	public HashMap<String, String> cadastrarProdutoSimilar() {
		System.out.printf("%-18s%s%-20s%s%-18s", "--------------------", "  ", "Cadastro de Produtos Similar", " ",
				"---------------------");
		System.out.println();
		HashMap<String, String> response = new HashMap<String, String>();
		boolean inputConcluido = false;
		do {
			try {
				System.out.print("Informe o nome do produto: ");
				String nome = sc.nextLine();
				System.out.print("Informe a Preço unitario do produto: ");
				String preco = sc.nextLine();
				float precoTest = Float.parseFloat(preco);
				System.out.print("Informe a descrição do produto: ");
				String descricao = sc.nextLine();
				System.out.print("Informe o id do Produto Original: ");
				String produtoOriginal = sc.nextLine();
				float produtoIdTest = Float.parseFloat(produtoOriginal);
				if (nome.length() > 0 && descricao.length() > 0) {
					response.put("nome", nome);
					response.put("preco", preco);
					response.put("descricao", descricao);
					response.put("produtoOriginal", produtoOriginal);
					inputConcluido = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Erro: Informações invalida, tente novamente");
			}
		} while (!inputConcluido);
		return response;
	}

	public static void listaProdutos(List<Produto> produtos) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ", "X Y Z Comercio LTDA", "  ",
				"-----------------------");
		System.out.println();
		System.out.printf("%-20s%s%-20s%s%-18s", "----------------------", "  ", "Listagem de Produtos", "  ",
				"-----------------------");
		System.out.println();
		System.out.println();
		if (!produtos.isEmpty()) {
			System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s", "ID", " ", "Nome do Produto", " ",
					"Departamento", "  ", "Preço", " ", "Quantidade", " ", "Prod Marca");
			System.out.println();
			System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s", "----", " ", "----------------", " ",
					"------------", "  ", "-------", " ", "----------", " ", "----------");
			System.out.println();
			for (Produto produto : produtos) {
				if (produto instanceof ProdutoEletronico) {
					ProdutoEletronico produto1 = (ProdutoEletronico) produto;
					System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s", produto1.getId(), " ",
							produto1.getNome(), " ", produto1.getDepartamento().getNome(), " ", produto1.getPreco(),
							" ", produto1.getQuantidade(), " ", produto1.ehProdutoMarca());
					System.out.println();
					System.out.println();
					System.out.println("Descrição: " + produto1.getDescricao());
					System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s", "----", " ", "----------------",
							" ", "------------", "  ", "-------", " ", "----------", " ", "----------");
					System.out.println();
				} else {
					System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s", produto.getId(), " ",
							produto.getNome(), " ", produto.getDepartamento().getNome(), " ", produto.getPreco(), " ",
							produto.getQuantidade(), " ", false);
					System.out.println();
					System.out.println();
					System.out.println("Descrição: " + produto.getDescricao());
					System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s", "----", " ", "----------------",
							" ", "------------", "  ", "-------", " ", "----------", " ", "----------");
					System.out.println();
				}
			}
			System.out.println();
		} else {
			System.out.println("Nenhum produto cadastrado.");
		}
	}

	public int buscarProdutoRequest() {
		boolean inputConcluido = false;
		int idProduto = 0;
		do {
			try {
				System.out.print("Informe o id do produto: ");
				idProduto = sc.nextInt();
				inputConcluido = true;
			}catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}

		}while(!inputConcluido);
		return idProduto;
	}
	public void listarProdutoSimilar(List<ProdutoEletronico> listaSimilares) {
		System.out.printf("%-5s%-2s%-40s%-2s%-16s","Id", " ", "Nome do Produto Similar", " ", "Preço");
		System.out.println();
		System.out.printf("%-5s%-2s%-40s%-2s%-16s","-".repeat(5), " ", "-".repeat(40), " ", "-".repeat(16));
		System.out.println();
		if(listaSimilares != null) {
			for(ProdutoEletronico produto: listaSimilares) {
				System.out.printf("%-5s%-2s%-40s%-2s%-16s", produto.getId(), " ", produto.getNome(), " ", produto.getPreco());
				System.out.println();
			}
		}
		else {
			System.out.println("O produto buscado não possui nenhum produto similar.");
		}
		System.out.println();
	}
}
