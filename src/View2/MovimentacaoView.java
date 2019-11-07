package View2;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Funcionario;

public class MovimentacaoView {
	private Scanner sc = new Scanner(System.in);

	public HashMap<String, Integer> dadosMoverProduto() {
		HashMap<String, Integer> response = new HashMap<String, Integer>();
		boolean inputConcluido = false;
		do {
			try {
				System.out.print("Informe o id do produto: ");
				Integer idProduto = sc.nextInt();
				response.put("idProduto", idProduto);
				System.out.print("Informe id do novo departamento: ");
				Integer idDepartamento = sc.nextInt();
				response.put("idDepartamento", idDepartamento);
				inputConcluido = true;
			} catch (InputMismatchException e) {
				System.out.println("Erro: Informações invalida, tente novamente");
				sc.next();
			}
		} while (!inputConcluido);
		return response;
	}

	public HashMap<String, Integer> dadosComprarProduto() {
		HashMap<String, Integer> response = new HashMap<String, Integer>();
		boolean inputConcluido = false;
		do {
			try {
				System.out.print("Informe o id do produto: ");
				Integer idProduto = sc.nextInt();
				response.put("idProduto", idProduto);
				System.out.print("Informe a quantidade a ser comprada: ");
				Integer quantidade = sc.nextInt();
				response.put("quantidade", quantidade);
				inputConcluido = true;
			} catch (InputMismatchException e) {
				System.out.println("Erro: Informações invalida, tente novamente");
				sc.next();
			}
		} while (!inputConcluido);
		return response;
	}

	public HashMap<String, String> dadosMoverFuncionario() {
		HashMap<String, String> response = new HashMap<String, String>();
		System.out.print("Informe a matricula do funcionario: ");
		String matricula = sc.next();
		response.put("matricula", matricula);
		System.out.print("Informe id do novo departamento: ");
		String idDepartamento = sc.next();
		response.put("idDepartamento", idDepartamento);
		return response;
	}

	public int solicitarDepartamento() {
		boolean inputConcluido = false;
		int idDepartamento = 0;
		do {
			try {
				System.out.print("Informe o id do departamento: ");
				idDepartamento = sc.nextInt();
				inputConcluido = true;
			} catch (InputMismatchException e) {
				System.out.println("Erro: Informações invalida, tente novamente");
				sc.next();
			}
		} while (!inputConcluido);
		return idDepartamento;

	}

	public void listarFuncionariosDepartamento(List<Funcionario> listaFuncionarios) {
		if (!listaFuncionarios.isEmpty()) {
			System.out.printf("%-20s%-10s%-15s", "Matricula", " ".repeat(10), "Nome");
			System.out.println();
			System.out.printf("%-20s%-10s%-15s", "-".repeat(20), " ".repeat(10), "-".repeat(15));
			System.out.println();
			for (Funcionario funcionario : listaFuncionarios) {
				System.out.printf("%-20s%-10s%-15s", funcionario.getMatricula(), " ".repeat(10), funcionario.getNome());
				System.out.println();
			}
		} else {
			System.out.println("Nenhum funcionario cadastrado no departamento.");
		}
	}

	public String solicitarMatricularFuncionario() {
		boolean inputConcluido = false;
		String matricula;
		do {
			System.out.print("Informe a matricula do funcionario: ");
			matricula = sc.nextLine();
			if (!matricula.isEmpty()) {
				inputConcluido = true;
			}
		} while (!inputConcluido);
		return matricula;
	}

	public double solicitarNovaComissao() {
		boolean inputConcluido = false;
		double comissao = 0;
		do {
			try {
				System.out.print("Informe a nova comissão [Ex 10]: ");
				comissao = sc.nextDouble();
				inputConcluido = true;

			} catch (InputMismatchException e) {
				sc.next();
				System.out.println(e.getMessage());
			}
		} while (!inputConcluido);
		return comissao;
	}
}
