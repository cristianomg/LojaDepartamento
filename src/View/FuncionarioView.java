package View;

import java.util.List;

import Model.Entites.Funcionario;

public class FuncionarioView {
	public static void listarFuncionarios(List<Funcionario> lista) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println();
		System.out.printf("%-19s%s%-20s%s%-20s", "--------------------", "  ","Listagem de Funcionarios"," ", "----------------------");
		System.out.println();
		System.out.println();
		System.out.printf("%-10s%-2s%-36s%-2s%-12s%-2s%s","Matricula", " ","Nome do Funcionario", " ", "departamento", " ", "Chefe");
		System.out.println();
		System.out.printf("%-10s%-2s%-36s%-2s%-12s%-2s%s","----------", " ","------------------------------------", " ", "------------", " ", "-----");
		System.out.println();
		for(Funcionario funcionario: lista) {
			System.out.println();
			System.out.printf("%-10s%-2s%-36s%-2s%-12s%-2s%s",funcionario.getMatricula(), " ",funcionario.getNome(), " ", funcionario.getDepartamento().getNome(), " ", funcionario.isChefe());
			System.out.println();
		}
		System.out.println();
	}
	
}