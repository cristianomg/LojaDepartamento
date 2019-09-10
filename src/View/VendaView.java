package View;

import java.util.List;

import Model.Entites.Departamento;
import Model.Entites.Produto;

public class VendaView {
	
		
	public static void listaProdutosDepartamento(List<Produto> produtos, Departamento departamento) {
	System.out.printf("%-4s%-1s%-20s%-10s%-10s%-10S","ID", " ","Nome do Produto", "  ", "Preço", " ", "Quantidade", " ", "Prod Marca");
	System.out.println();
	System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-8s%-2s%-10s%-2s%s","----", " ","----------------", " ","------------", "  ", "--------", " ", "----------", " ", "----------");
	System.out.println();
	for(Produto produto: produtos) {
		if (produto.getDepartamento().equals(departamento)) {
			System.out.printf("%-4s%-1s%-17s%-1s%-13s%-1s%-5s%-2s%-10s%-2s%s",produto.getId(), " ",produto.getNome()," ", produto.getDepartamento().getNome(), " ", produto.getPreco(), " ", produto.getQuantidade(), " ", produto.ehProdutoMarca());
			System.out.println();
			System.out.println();
			System.out.println("Descrição: "+ produto.getDescricao());
			System.out.printf("%-4s%-1s%-17ss%-1s%-13s%-1s%-5s%-2s%-10s%-2s%s","----", " ","----------------", " ","------------", "  ", "-----", " ", "----------", " ", "----------");
			System.out.println();
		}
	}
}

}
