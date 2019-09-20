package View;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Entites.Cliente;

public class ClienteView {
	private Scanner sc = new Scanner(System.in);
	
	public HashMap<String, String> cadastroCliente() {
		boolean inputConcluido = false;
		HashMap<String, String> retornoCliente = new HashMap<String, String>();
		String nome;
		String cpf_cnpj;
		do {
			System.out.print("Informe o nome do cliente: ");
			nome = sc.nextLine();
			System.out.print("Informe o cpf ou cnpj do cliente:");
			cpf_cnpj = sc.nextLine();
			if(nome.length() > 0 && cpf_cnpj.length() > 0) {
				retornoCliente.put("nome", nome);
				retornoCliente.put("cpf_cnpj", cpf_cnpj);
				inputConcluido = true;
			}
			else {
				System.out.println("Erro: Informações insuficientes, tente novamente.");
			}
		}while(!inputConcluido);
		return retornoCliente;
		
	}
	public HashMap<String, String> cadastrarEndereco(){
		boolean inputConcluido = false;
		HashMap<String, String> retornoEndereco = new HashMap<String, String>();
		do {
			System.out.print("Informe o nome da rua: ");
			String rua = sc.nextLine();
			System.out.print("Informe o numero: ");
			String numero = sc.nextLine();
			System.out.print("Informe o bairro: ");
			String bairro = sc.nextLine();
			System.out.print("Informe o cep: ");
			String cep = sc.nextLine();
			System.out.print("Informe a cidade: ");
			String cidade = sc.nextLine();
			System.out.print("Informe o estado: ");
			String estado = sc.nextLine();

			if(rua.length()>0 && numero.length() > 0 && bairro.length() > 0 && cep.length() > 0 && cidade.length() > 0 && estado.length() > 0){
				retornoEndereco.put("rua", rua);
				retornoEndereco.put("numero",  numero);
				retornoEndereco.put("bairro", bairro);
				retornoEndereco.put("cep", cep);
				retornoEndereco.put("cidade", cidade);
				retornoEndereco.put("estado", estado);
				inputConcluido = true;
			}
			else {
				System.out.println("Erro: Informações insuficientes, tente novamente.");
			}
		}while(!inputConcluido);
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
