package View;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Produto;
import Model.Entites.ProdutoEletronico;

public class ProdutoView {
	private Scanner sc = new Scanner(System.in);
	
	public HashMap<String, String> cadastrarProduto() {
		System.out.printf("%-18s%s%-20s%s%-18s", "--------------------", "  ","Cadastro de Produtos"," ", "---------------------");
		System.out.println();
		HashMap<String, String> response = new HashMap<String, String>();
		System.out.print("Informe o nome do produto: ");
		String nome = sc.nextLine();
		response.put("nome", nome);
		System.out.print("Informe a Preço unitario do produto: ");
		String preco = sc.nextLine();
		response.put("preco", preco);
		System.out.print("Informe a descrição do produto: ");
		String descricao = sc.nextLine();
		response.put("descricao", descricao);
		System.out.print("Informe o Id do departamento que o produto será vendido: ");
		String idDepartamento = sc.nextLine();
		response.put("idDepartamento", idDepartamento);
		return response;
	}
	
	public HashMap<String, String> cadastrarProdutoSimilar() {
		System.out.printf("%-18s%s%-20s%s%-18s", "--------------------", "  ","Cadastro de Produtos Similar"," ", "---------------------");
		System.out.println();
		HashMap<String, String> response = new HashMap<String, String>();
		System.out.print("Informe o nome do produto: ");
		String nome = sc.nextLine();
		response.put("nome", nome);
		System.out.print("Informe a Preço unitario do produto: ");
		String preco = sc.nextLine();
		response.put("preco", preco);
		System.out.print("Informe a descrição do produto: ");
		String descricao = sc.nextLine();
		response.put("descricao", descricao);
		System.out.print("Informe o id do Produto Original: ");
		String produtoOriginal = sc.nextLine();
		response.put("produtoOriginal", produtoOriginal);
		return response;
	}
	
	public static void listaProdutos(List<Produto> produtos) {
	System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
	System.out.println();
	System.out.printf("%-20s%s%-20s%s%-18s", "----------------------", "  ","Listagem de Produtos","  ", "-----------------------");
	System.out.println();
	System.out.println();
	System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s","ID", " ","Nome do Produto", " ","Departamento", "  ", "Preço", " ", "Quantidade", " ", "Prod Marca");
	System.out.println();
	System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s","----", " ","----------------", " ","------------", "  ", "-------", " ", "----------", " ", "----------");
	System.out.println();
	for(Produto produto: produtos) {
		if(produto instanceof ProdutoEletronico) {
			ProdutoEletronico produto1 = (ProdutoEletronico) produto;
			System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s",produto1.getId(), " ",produto1.getNome()," ", produto1.getDepartamento().getNome(), " ", produto1.getPreco(), " ", produto1.getQuantidade(), " ", produto1.ehProdutoMarca());
			System.out.println();
			System.out.println();
			System.out.println("Descrição: "+ produto1.getDescricao());
			System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s","----", " ","----------------", " ","------------", "  ", "-------", " ", "----------", " ", "----------");
			System.out.println();
		}
		else {
			System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s",produto.getId(), " ",produto.getNome()," ", produto.getDepartamento().getNome(), " ", produto.getPreco(), " ", produto.getQuantidade(), " ", false);
			System.out.println();
			System.out.println();
			System.out.println("Descrição: "+ produto.getDescricao());
			System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s","----", " ","----------------", " ","------------", "  ", "-------", " ", "----------", " ", "----------");
			System.out.println();
		}
		}
	System.out.println();
	}
	public static void buscarProdutoRequest() {
		
	}
}
