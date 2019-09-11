package View;

import java.util.List;

import Model.Entites.Produto;

public class ProdutoView {
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
		System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s",produto.getId(), " ",produto.getNome()," ", produto.getDepartamento().getNome(), " ", produto.getPreco(), " ", produto.getQuantidade(), " ", produto.ehProdutoMarca());
		System.out.println();
		System.out.println();
		System.out.println("Descrição: "+ produto.getDescricao());
		System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-7s%-2s%-10s%-2s%s","----", " ","----------------", " ","------------", "  ", "-------", " ", "----------", " ", "----------");
		System.out.println();
		}
	System.out.println();
	}
}
