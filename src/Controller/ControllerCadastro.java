package Controller;

import java.util.HashMap;
import Exceptions.DepartamentoNaoEncontradoException;
import Exceptions.ProdutoNaoEncontradoException;
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
import View.DepartamentoView;
import View.FuncionarioView;
import View.ProdutoView;

public class ControllerCadastro {
	private static ClienteDAO clientes = ClienteDAO.getInstance();
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	
	public static void cadastrarCliente() {
		HashMap<String, String>infoCliente = ClienteView.cadastroCliente();
		String nome = infoCliente.get("nome");
		String cpf_cnpj = infoCliente.get("cpf_cnpj");
		try {
			int id = clientes.getLista().size();
			Cliente cliente = new Cliente(id, nome, cpf_cnpj);
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
		String est = (String) dadosEndereco.get("estado");
		String cid = (String) dadosEndereco.get("cidade");
		String r = (String) dadosEndereco.get("rua");
		Estado estado = new Estado(est); // 
		Cidade cidade = new Cidade(cid, estado); // 
		String rua = r; 
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
		HashMap<String, String> request = DepartamentoView.cadastrarDepartamento();
		String nome = request.get("nome");
		String sigla = request.get("sigla");
		int idDepartamento = departamentos.getLista().size();
		Departamento dp = new Departamento(idDepartamento, nome, sigla);
		departamentos.inserir(dp);
		System.out.println("Departamento Cadastrado com sucesso!!!");
	}
	
	public static void cadastrarFuncionario() {
		int idDepartamento = FuncionarioView.solicitarDepartamentoID().get("idFuncionario");
		try {
		Departamento departamento = departamentos.getDepartamento(idDepartamento);
		HashMap<String, String> request = FuncionarioView.cadastrarFuncionario();
		String nome = request.get("nome");
		String matricula = request.get("matricula");
		String senha = request.get("senha");
		Funcionario f = new Funcionario(nome, matricula, senha); // CRIAR VIEW
		f.setDepartamento(departamento);
		funcionarios.inserir(f);
		System.out.println(funcionarios.getLista().get(0));
		System.out.println("Funcionario Cadastrado com sucesso!!!");
		}
		catch (DepartamentoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void cadastrarProduto() {
		HashMap<String, String> request = ProdutoView.cadastrarProduto();
		String nome = request.get("nome");
		String descricao = request.get("descricao");
		float preco = Float.parseFloat(request.get("preco"));
		int quantidade = 0;
		int idDepartamento = Integer.parseInt(request.get("idDepartamento"));
		try {
			Departamento departamento = departamentos.getDepartamento(idDepartamento);
			int idProduto = produtos.getLista().size();
			Produto p = new Produto(idProduto, nome, descricao, preco, quantidade, departamento);
			produtos.inserir(p);
			System.out.println("Produto Cadastrado com sucesso!!!");
		}
		catch (DepartamentoNaoEncontradoException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void cadastrarProdutoSimilar() {
		HashMap<String, String> request = ProdutoView.cadastrarProdutoSimilar();
		String nome = request.get("nome");
		String descricao = request.get("descricao");
		float preco = Float.parseFloat(request.get("preco"));
		int quantidade = 0;
		int idProdutoOriginal = Integer.parseInt(request.get("produtoOriginal"));
		try {
			Produto produtoOriginal = produtos.getProduto(idProdutoOriginal);
			Departamento departamento = produtoOriginal.getDepartamento() ;
			int idProduto = produtos.getLista().size();
			Produto p = new Produto(idProduto, nome, descricao, preco, quantidade, departamento);
			p.setProdutoMarca(produtoOriginal);
			produtoOriginal.cadastrarSimilar(p);
			produtos.inserir(p);
			System.out.println("Produto Cadastrado com sucesso!!!");
		}
		catch (ProdutoNaoEncontradoException e){
			System.out.println(e.getMessage());
		}
	}
}

