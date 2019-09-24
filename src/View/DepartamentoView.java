package View;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Departamento;
import Model.Entites.Produto;

public class DepartamentoView {
	static Scanner sc = new Scanner(System.in);
	
	public HashMap<String, String> cadastrarDepartamento() {
		System.out.printf("%-18s%s%-20s%s%-18s", "--------------------", "  ","Cadastro de Departamentos"," ", "---------------------");
		System.out.println();
		HashMap<String, String> response = new HashMap<String, String>();
		boolean inputConcluido = false;
		do {
			System.out.print("Informe o nome do Departamento: ");
			String nome = sc.nextLine();
			System.out.print("Informe a Sigla:");
			String sigla = sc.nextLine();
			if (nome.length() > 0 && sigla.length() > 0) {
				response.put("nome", nome);
				response.put("sigla", sigla);
				inputConcluido = true;
			}
			else {
				System.out.println("Erro: Informações insuficientes, tente novamente.");
			}
		}while(!inputConcluido);
		return response;
	}
		
	public static void listarDepartamentos(List<Departamento> departamentos) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println();
		System.out.printf("%-18s%s%-20s%s%-18s", "--------------------", "  ","Listagem de Departamentos"," ", "---------------------");
		System.out.println();
		System.out.println();
		if(!departamentos.isEmpty()) {
			for(Departamento departamento: departamentos) {
				System.out.printf("%-4s%-2s%-50s%-2s%-11s","ID", " ","Nome do Departamento", " ", "Sigla");
				System.out.println();
				System.out.printf("%-4s%-2s%-50s%-2s%-11s","----", " ","------------------------------------------------", " ", "-----------");
				System.out.println();
				System.out.printf("%-4s%-2s%-50s%-2s%-11s",departamento.getId(), " ",departamento.getNome(), " ", departamento.getSigla());
				System.out.println();
				System.out.println();
				System.out.println("Lista de Produtos");
				System.out.printf("%-4s%-2s%-38s%-2s%-10s%-2s%-5s","----", " ","-------------------------------------", " ", "-----", " ", "-----------");
				System.out.println();
				System.out.printf("%-4s%-2s%-38s%-2s%-10s%-2s%-5s","ID", " ","Nome dos Produtos do Departamento", " ", "Preço", " ", "Quantidade");
				System.out.println();
				System.out.printf("%-4s%-2s%-38s%-2s%-10s%-2s%-5s","----", " ","-------------------------------------", " ", "-----", " ", "-----------");
				System.out.println();
				for(Produto produto: departamento.getProdutos()) {
					System.out.printf("%-4s%-2s%-38s%-2s%-10s%-2s%-5s", produto.getId(), " ",produto.getNome(), " ", produto.getPreco(), " ", produto.getQuantidade());
					System.out.println();
				}
			}
			System.out.println();
		}else {
			System.out.println("Nenhum departamento cadastrado.");
		}

	}
}
