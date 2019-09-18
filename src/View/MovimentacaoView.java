package View;

import java.util.HashMap;
import java.util.Scanner;

public class MovimentacaoView {
	private Scanner sc = new Scanner(System.in);
	public HashMap<String, Integer> dadosMoverProduto(){
		HashMap<String, Integer> response = new HashMap<String, Integer>();
		System.out.print("Informe o id do produto: ");
		Integer idProduto = sc.nextInt();
		response.put("idProduto", idProduto);
		System.out.print("Informe id do novo departamento: ");
		Integer idDepartamento = sc.nextInt();
		response.put("idDepartamento", idDepartamento);
		return response;
	}
	
	public HashMap<String, Integer> dadosComprarProduto(){
		HashMap<String, Integer> response = new HashMap<String, Integer>();
		System.out.print("Informe o id do produto: ");
		Integer idProduto = sc.nextInt();
		response.put("idProduto", idProduto);
		System.out.print("Informe a quantidade a ser comprada: ");
		Integer quantidade = sc.nextInt();
		response.put("quantidade", quantidade);
		return response;
	}
	
	public HashMap<String, String> dadosMoverFuncionario(){
		HashMap<String, String> response = new HashMap<String, String>();
		System.out.print("Informe a matricula do funcionario: ");
		String matricula = sc.next();
		response.put("matricula", matricula);
		System.out.print("Informe id do novo departamento: ");
		String idDepartamento = sc.next();
		response.put("idDepartamento", idDepartamento);
		return response;
	}
}
