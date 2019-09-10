package View;

import java.util.List;

import Model.Entites.Departamento;
import Model.Entites.Produto;

public class VendaView {
	
		
	public static void listaProdutosDepartamento(List<Produto> produtos, Departamento departamento) {
		System.out.println("Produtos do departamento: ");
		System.out.printf("%-58s","---------------------------------------------------------------------");
		System.out.println();
		System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s","ID", " ","Nome do Produto", " ", "Preço", " ", "Quantidade", " ", "Prod Marca");
		System.out.println();
		System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s","----", " ","--------------------------", " ", "--------", " ", "----------", " ", "----------");
		System.out.println();
		for(Produto produto: produtos) {
			if (produto.getDepartamento().equals(departamento)) {
				System.out.printf("%-4s%-1s%-30s%-1s%-10s%-1s%-10s%-1s%s",produto.getId(), " ",produto.getNome(), " ", produto.getPreco(), " ", produto.getQuantidade(), " ", produto.ehProdutoMarca());
				System.out.println();
				System.out.println();
				System.out.println("Descrição: "+ produto.getDescricao());
				System.out.printf("%-58s","---------------------------------------------------------------------");
				System.out.println();
			}
		}
	}

}
