package View;

import java.util.List;

import Model.Entites.Departamento;
<<<<<<< HEAD
import Model.Entites.Produto;

public class DepartamentoView {
	public static void listarDepartamentos(List<Departamento> departamentos) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println();
		System.out.printf("%-19s%s%-20s%s%-20s", "-------------------", "  ","Listagem de Departamentos"," ", "----------------------");
=======

public class DepartamentoView {
	public static void listarClientes(List<Departamento> departamentos) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println();
		System.out.printf("%-20s%s%-20s%s%-20s", "----------------------", "   ","Listagem de Departamentos"," ", "-----------------------");
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
		System.out.println();
		System.out.println();
		System.out.printf("%-4s%-2s%-50s%-2s%-11s","ID", " ","Nome do Departamento", " ", "Sigla");
		System.out.println();
		System.out.printf("%-4s%-2s%-50s%-2s%-11s","----", " ","------------------------------------------------", " ", "-----------");
		for(Departamento departamento: departamentos) {
			System.out.println();
			System.out.printf("%-4s%-2s%-50s%-2s%-11s",departamento.getId(), " ",departamento.getNome(), " ", departamento.getSigla());
<<<<<<< HEAD
			System.out.println();
			System.out.printf("%-4s%-2s%-38s%-2s%-10s%-2s%-5s","ID", " ","Nome dos Produtos do Departamento", " ", "Pre�o", " ", "Quantidade");
			System.out.println();
			System.out.printf("%-4s%-2s%-38s%-2s%-10s%-2s%-5s","----", " ","-------------------------------------", " ", "-----", " ", "-----------");
			System.out.println();
			for(Produto produto: departamento.getProdutos()) {
				System.out.printf("%-4s%-2s%-38s%-2s%-10s%-2s%-5s", produto.getId(), " ",produto.getNome(), " ", produto.getPreco(), " ", produto.getQuantidade());
			}
=======
			
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
		}
		System.out.println();
		
		
	}
}
