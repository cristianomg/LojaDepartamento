package View;

import java.util.List;

import Model.Entites.Departamento;

public class DepartamentoView {
	public static void listarClientes(List<Departamento> departamentos) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println();
		System.out.printf("%-20s%s%-20s%s%-20s", "----------------------", "   ","Listagem de Departamentos"," ", "-----------------------");
		System.out.println();
		System.out.println();
		System.out.printf("%-4s%-2s%-50s%-2s%-11s","ID", " ","Nome do Departamento", " ", "Sigla");
		System.out.println();
		System.out.printf("%-4s%-2s%-50s%-2s%-11s","----", " ","------------------------------------------------", " ", "-----------");
		for(Departamento departamento: departamentos) {
			System.out.println();
			System.out.printf("%-4s%-2s%-50s%-2s%-11s",departamento.getId(), " ",departamento.getNome(), " ", departamento.getSigla());
			
		}
		System.out.println();
		
		
	}
}
