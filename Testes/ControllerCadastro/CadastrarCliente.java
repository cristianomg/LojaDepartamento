package ControllerCadastro;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import Exceptions.ValidateCpfException;
import Model.Entites.Cliente;
import Model.Entites.Logradouro.Cidade;
import Model.Entites.Logradouro.Endereco;
import Model.Entites.Logradouro.Estado;

public class CadastrarCliente {
	private static List<Cliente> clientes = new ArrayList<Cliente>();

	public String cadastrarCliente(HashMap<String, String>infoCliente) {
		String nome = infoCliente.get("nome");
		String cpf_cnpj = infoCliente.get("cpf_cnpj");
		Estado estado = new Estado(infoCliente.get("estado"));
		Cidade cidade = new Cidade(infoCliente.get("cidade"), estado);
		String rua =  infoCliente.get("rua");
		int numero = Integer.parseInt(infoCliente.get("numero"));  
		String bairro = infoCliente.get("bairro");
		String cep = infoCliente.get("cep");
		try {
			int id = clientes.size();
			Cliente cliente = new Cliente(id, nome, cpf_cnpj);
			Endereco endereco = cadastrarEndereco(rua, numero, bairro, cep, cidade, estado);
			cliente.setEndereco(endereco);
			if (!clientes.contains(cliente)) {
				clientes.add(cliente);
				return "Cliente cadastrado com sucesso!!!";
			}
			else {
				return "Erro: Já existe um cliente cadastrado com esse cpf_cnpj";
			}
		}
		catch(ValidateCpfException e){
			return e.getMessage();
		}
	}
	
	private Endereco cadastrarEndereco(String rua , int numero, String bairro, String cep, Cidade cidade, Estado estado) {
		Endereco endereco = new Endereco(rua , numero, bairro, cep, cidade, estado);
		cidade.addEnderecoLista(endereco);
		estado.addCidadeLista(cidade);
		return endereco;
	}
	@Test
	public void test() {
		String expected = "Cliente cadastrado com sucesso!!!";
		HashMap<String, String> infoCLiente = new HashMap<String, String>();
		infoCLiente.put("nome", "teste1");
		infoCLiente.put("cpf_cnpj", "00000000000");
		infoCLiente.put("rua", "teste");
		infoCLiente.put("numero",  "0000");
		infoCLiente.put("bairro", "teste");
		infoCLiente.put("cep", "00000000");
		infoCLiente.put("cidade", "teste");
		infoCLiente.put("estado", "teste");
		String actual = cadastrarCliente(infoCLiente);
		assertEquals(expected, actual);
		assertEquals(1, clientes.size());
	}

}
