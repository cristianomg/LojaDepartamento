package View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Funcionario;
import Model.Entites.Venda;

public class FuncionarioView {
	private Scanner sc = new Scanner(System.in);
	
	public HashMap<String, Integer> solicitarDepartamentoID() {
		System.out.printf("%-18s%s%-20s%s%-18s", "--------------------", "  ","Cadastro de Funcionarios"," ", "---------------------");
		System.out.println();
		boolean inputConcluido = false;
		HashMap<String, Integer> response = new HashMap<String, Integer>();
		do {
			try {
				System.out.print("Informe o ID do departamento que deseja cadastrar o funcionario: ");
				Integer idDepartamento = Integer.parseInt(sc.nextLine());
				response.put("idDepartamento", idDepartamento);
				inputConcluido = true;
			}
			catch (NumberFormatException e) {
				System.out.println("Erro: informação invalida, tente novamente.");
			}
		}while(!inputConcluido);
		return response;
	}
	
	public HashMap<String, String> cadastrarFuncionario() {
		HashMap<String, String> response = new HashMap<String, String>();
		boolean inputConcluido = false;
		do {
			System.out.print("Informe o nome do funcionario: ");
			String nome = sc.nextLine();
			System.out.print("Informe a matricula do funcionario: ");
			String matricula = sc.nextLine();
			System.out.print("Informe a senha do funcionario: ");
			String senha = sc.nextLine();
			if(nome.length() > 0 && matricula.length() > 0 && senha.length() > 0) {
				response.put("nome", nome);
				response.put("matricula", matricula);
				response.put("senha", senha);
				inputConcluido = true;
			}
		}while(!inputConcluido);
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
	@SuppressWarnings("unused")
	public HashMap<String, String> buscarFuncionarioRequest() {
		HashMap<String, String> response = new HashMap<String, String>();
		boolean inputConcluido = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		do { 
			try {
				System.out.print("Informe a matricula do vendedor: ");
				String matricula= sc.nextLine();
				System.out.print("Informe a data inicial da busca [dd/MM/yyyy]: ");
				String dataInicial = sc.nextLine();
				LocalDate dataInicioTest = LocalDate.parse(dataInicial, formatter);
				System.out.print("Informe a data final da busca [dd/MM/yyyy]: ");
				String dataFinal = sc.nextLine();
				LocalDate dataFinalTest = LocalDate.parse(dataFinal, formatter);
				if(matricula.length() > 0) {
					response.put("matricula", matricula);
					response.put("dataInicial", dataInicial);
					response.put("dataFinal", dataFinal);
					inputConcluido = true;
				}
			}catch(DateTimeParseException e) {
				System.out.println("Erro: Data com formato invalido, tente novamente;");
			}
		}while(!inputConcluido);
		return response;
	}

	public void buscarFuncionarioResponse(Funcionario funcionario, List<Venda> vendasPeriodo, LocalDate dataInicio, LocalDate dataFinal) {
		String str = "-";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		System.out.println(str.repeat(69));
		System.out.printf("%-20s%-30s%-10s%s", "Nome do Funcionario: ",funcionario.getNome(), "matricula", funcionario.getMatricula());
		System.out.println();
		System.out.println(str.repeat(69));
		System.out.println("Vendas efetuadas no periodo de " + dataInicio.format(formatter) +" à " + dataFinal.format(formatter));
		System.out.println();
		if(vendasPeriodo.size()>0) {
			System.out.printf("%-30s%-10s", "Codigo da Venda", "Data");
			System.out.println(str.repeat(69));
			for(Venda venda: vendasPeriodo) {
				System.out.printf("%-30s%-10s",venda.getCodigo(), venda.getData().format(formatter));
			}
		}
		else {
			System.out.println("Nenhuma venda encontrada.");
		}
	}
}