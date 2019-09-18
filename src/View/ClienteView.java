package View;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Cliente;

public class ClienteView {
	private Scanner sc = new Scanner(System.in);
	
	public HashMap<String, String> cadastroCliente() {
		HashMap<String, String> retornoCliente = new HashMap<String, String>();
		System.out.print("Informe o nome do cliente: ");
		String nome = sc.nextLine();
		retornoCliente.put("nome", nome);
		System.out.print("Informe o cpf ou cnpj do cliente:");
		String cpf_cnpj = sc.nextLine();
		retornoCliente.put("cpf_cnpj", cpf_cnpj);
		return retornoCliente;
		
	}
	public HashMap<String, String> cadastrarEndereco(){
		HashMap<String, String> retornoEndereco = new HashMap<String, String>();
		System.out.print("Informe o nome da rua: ");
		String rua = sc.nextLine();
		retornoEndereco.put("rua", rua);
		System.out.print("Informe o numero: ");
		String numero = sc.nextLine();
		retornoEndereco.put("numero",  numero);
		System.out.print("Informe o bairro: ");
		String bairro = sc.nextLine();
		retornoEndereco.put("bairro", bairro);
		System.out.print("Informe o cep: ");
		String cep = sc.nextLine();
		retornoEndereco.put("cep", cep);
		System.out.print("Informe a cidade: ");
		String cidade = sc.nextLine();
		retornoEndereco.put("cidade", cidade);
		System.out.print("Informe o estado: ");
		String estado = sc.nextLine();
		retornoEndereco.put("estado", estado);
		return retornoEndereco;
		
	}
	public static void listarClientes(List<Cliente> clientes) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println();
		System.out.printf("%-20s%s%-20s%s%-20s", "----------------------", "   ","Listagem de Clientes"," ", "-----------------------");
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
