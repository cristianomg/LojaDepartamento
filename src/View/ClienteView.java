package View;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Cliente;

public class ClienteView {
	static Scanner sc = new Scanner(System.in);
	public static HashMap<String, String> cadastroCliente() {
		HashMap<String, String> retornoCliente = new HashMap<String, String>();
		System.out.print("Informe o nome do cliente: ");
		String nome = sc.nextLine();
		retornoCliente.put("nome", nome);
		System.out.print("Informe o cpf ou cnpj do cliente:");
		String cpf_cnpj = sc.nextLine();
		retornoCliente.put("cpf_cnpj", cpf_cnpj);
		return retornoCliente;
		
	}
	public static <T> HashMap<String, T> cadastrarEndereco(){
		HashMap<String, T> retornoEndereco = new HashMap<String, T>();
		System.out.print("Informe o nome da rua: ");
		String rua = sc.nextLine();
		retornoEndereco.put("rua", (T) rua);
		System.out.print("Informe o numero: ");
		String numero = sc.nextLine();
		retornoEndereco.put("numero", (T) numero);
		System.out.println("Informe o bairro: ");
		String bairro = sc.nextLine();
		retornoEndereco.put("bairro", (T) bairro);
		System.out.println("Informe o cep: ");
		String cep = sc.nextLine();
		retornoEndereco.put("cep", (T) cep);
		System.out.println("Informe a cidade: ");
		String cidade = sc.nextLine();
		retornoEndereco.put("cidade", (T) cidade);
		System.out.println("Informe o estado: ");
		String estado = sc.nextLine();
		retornoEndereco.put("estado", (T) estado);
		return retornoEndereco;
		
	}
	public static void listarClientes(List<Cliente> clientes) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println();
		System.out.printf("%-20s%s%-20s%s%-20s", "----------------------", "   ","Menu de cadastros"," ", "-----------------------");
		System.out.println();
		System.out.println();
		System.out.printf("%-4s%-2s%-50s%-2s%-11s","ID", " ","Nome do Cliente", " ", "CPF/CNPJ");
		System.out.println();
		System.out.printf("%-4s%-2s%-50s%-2s%-11s","----", " ","------------------------------------------------", " ", "-----------");
		for(Cliente cliente: clientes) {
			System.out.println();
			System.out.printf("%-4s%-2s%-50s%-2s%-11s",cliente.getId(), " ",cliente.getNome(), " ", cliente.getCpf_cnpj());
			
		}
		System.out.println();
		
		
	}
}
