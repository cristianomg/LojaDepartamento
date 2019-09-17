package View;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Funcionario;

public class FuncionarioView {
	static Scanner sc = new Scanner(System.in);
	
	public static HashMap<String, Integer> solicitarDepartamentoID() {
		System.out.printf("%-18s%s%-20s%s%-18s", "--------------------", "  ","Cadastro de Funcionarios"," ", "---------------------");
		System.out.println();
		HashMap<String, Integer> response = new HashMap<String, Integer>();
		System.out.print("Informe o ID do departamento que deseja cadastrar o funcionario: ");
		Integer idFuncionario = sc.nextInt();
		response.put("idFuncionario", idFuncionario);
		return response;
	}
	
	public static HashMap<String, String> cadastrarFuncionario() {
		System.out.println();
		HashMap<String, String> response = new HashMap<String, String>();
		System.out.print("Informe o nome do Funcionario: ");
		String nome = sc.nextLine();
		response.put("nome", nome);
		System.out.print("Informe a Matricula: ");
		String matricula = sc.nextLine();
		response.put("matricula", matricula);
		System.out.print("Informe a Senha: ");
		String senha = sc.nextLine();
		response.put("senha", senha);
		return response;
	}
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