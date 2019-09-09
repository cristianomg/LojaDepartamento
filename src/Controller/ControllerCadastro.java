package Controller;

import java.util.HashMap;

import Exceptions.ObjetoNaoEncontradoException;
import Exceptions.ValidateCpfException;
import Model.DAO.ClienteDAO;
import Model.DAO.DepartamentoDAO;
import Model.DAO.FuncionarioDAO;
import Model.DAO.ProdutoDAO;
import Model.Entites.Cliente;
import Model.Entites.Departamento;
import Model.Entites.Funcionario;
import Model.Entites.Produto;
import Model.Entites.Logradouro.Cidade;
import Model.Entites.Logradouro.Endereco;
import Model.Entites.Logradouro.Estado;
import View.ClienteView;

public class ControllerCadastro {
	private static ClienteDAO clientes = ClienteDAO.getInstance();
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	
	public static void cadastrarCliente() {
			HashMap<String, String>infoCliente = ClienteView.cadastroCliente();
		try {
			Cliente cliente = new Cliente(infoCliente.get("nome"), infoCliente.get("cpf_cnpj"));
			Endereco endereco = cadastrarEndereco();
			cliente.setEndereco(endereco);
			clientes.inserir(cliente);
			System.out.println("Cliente cadastrado com sucesso!!!");
		}
		catch(ValidateCpfException e){
			System.out.println(e);
		}
	}
	private static Endereco cadastrarEndereco() {
		HashMap<String, Object> dadosEndereco = ClienteView.cadastrarEndereco();
		Estado estado = new Estado((String) dadosEndereco.get("estado"));
		Cidade cidade = new Cidade((String) dadosEndereco.get("cidade"), estado);
		String rua = (String) dadosEndereco.get("rua");
		Endereco endereco = null;
		try {
			int numero = Integer.parseInt((String) dadosEndereco.get("numero"));
			String bairro = (String) dadosEndereco.get("bairro");
			String cep = (String) dadosEndereco.get("cep");
			endereco = new Endereco(rua , numero, bairro, cep, cidade, estado);
			cidade.addEnderecoLista(endereco);
			estado.addCidadeLista(cidade);
			
		}
		catch (NumberFormatException e) {
			  System.out.println("Numero com formato errado!");
			  endereco= null;
			  cadastrarEndereco();
		}

		return endereco;
	}
	public static void cadastrarDepartamento() {
		String nome = "eletronico";
		String sigla = "eletro";
		Departamento dp = new Departamento(nome, sigla);
		departamentos.inserir(dp);
		System.out.println(departamentos.getLista().get(0));
	}
	
	public static void cadastrarFuncionario() {
		int idDepartamento = 0;
		try {
		Departamento departamento = departamentos.getDepartamento(idDepartamento);
		Funcionario f = new Funcionario("Cristiano", "323232", "323232");
		f.setDepartamento(departamento);
		funcionarios.inserir(f);
		System.out.println(funcionarios.getLista().get(0));
		}
		catch (ObjetoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void cadastrarProduto() {
		String nome = "tv";
		String descricao = "led 43 polegadas";
		float preco = 19000;
		int quantidade = 300;
		try {
			Departamento departamento = departamentos.getDepartamento(0);
			Produto p = new Produto(nome, descricao, preco, quantidade, departamento);
			produtos.getLista().add(p);
		}
		catch (ObjetoNaoEncontradoException e){
			System.out.println(e.getMessage());
		}

		
	}
	
}
