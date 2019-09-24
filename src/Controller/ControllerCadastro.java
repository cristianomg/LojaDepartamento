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
import Model.Entites.ProdutoEletronico;
import Model.Entites.Logradouro.Cidade;
import Model.Entites.Logradouro.Endereco;
import Model.Entites.Logradouro.Estado;
import View.FuncionarioView;

public class ControllerCadastro {
	private static ClienteDAO clientes = ClienteDAO.getInstance();
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	
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
			int id = clientes.getLista().size();
			Cliente cliente = new Cliente(id, nome, cpf_cnpj);
			Endereco endereco = cadastrarEndereco(rua, numero, bairro, cep, cidade, estado);
			cliente.setEndereco(endereco);
			if (!clientes.testClienteExiste(cliente)) {
				clientes.inserir(cliente);
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
	public String cadastrarDepartamento(HashMap<String, String> dadosDepartamento) {
		String nome = dadosDepartamento.get("nome");
		String sigla = dadosDepartamento.get("sigla");
		int idDepartamento = departamentos.getLista().size();
		Departamento dp = new Departamento(idDepartamento, nome, sigla);
		departamentos.inserir(dp);
		return "Departamento Cadastrado com sucesso!!!";
	}
	
	public String cadastrarFuncionario(int idDepartamento) {
		FuncionarioView funcionarioView = new FuncionarioView();
		try {
			Departamento departamento = departamentos.getDepartamento(idDepartamento);
			HashMap<String, String> request = funcionarioView.cadastrarFuncionario();
			String nome = request.get("nome");
			String matricula = request.get("matricula");
			String senha = request.get("senha");
			String cursoSuperior = request.get("cursoSuperior");
			if(!funcionarios.testeFuncionarioExiste(matricula)) {
				Funcionario f = new Funcionario(nome, matricula, senha);
				if(cursoSuperior.equals("s")) {
					f.setEnsinoSuperior(true);
				}
				f.setDepartamento(departamento);
				departamento.addFuncionarioList(f);
				funcionarios.inserir(f);
				departamentos.atualizar(departamento);
				return "Funcionario Cadastrado com sucesso!!!";
			}
			else {
				return "Erro: Matricula já cadastrada, insira uma nova.";
			}
		}
		catch (DepartamentoNaoEncontradoException e) {
			return e.getMessage();
		}
	}
	public String cadastrarProduto(HashMap<String, String> dadosProduto ) {
		String nome = dadosProduto.get("nome");
		String descricao = dadosProduto.get("descricao");
		float preco = Float.parseFloat(dadosProduto.get("preco"));
		int quantidade = 0;
		int idDepartamento = Integer.parseInt(dadosProduto.get("idDepartamento"));
		try {
			Departamento departamento = departamentos.getDepartamento(idDepartamento);
			if (departamento.getNome().equals("Eletronico")) {
				int idProduto = produtos.getLista().size();
				ProdutoEletronico p = new ProdutoEletronico(idProduto, nome, descricao, preco, quantidade, departamento);
				produtos.inserir(p);
				return "Produto Eletronico Cadastrado com sucesso!!!";
			}
			else {
				int idProduto = produtos.getLista().size();
				Produto p = new Produto(idProduto, nome, descricao, preco, quantidade, departamento);
				produtos.inserir(p);
				return "Produto Cadastrado com sucesso!!!";
			}
		}
		catch (DepartamentoNaoEncontradoException e){
			return e.getMessage();
		}
	}
	
	public String cadastrarProdutoSimilar(HashMap<String, String> dadosProdutoSimilar) {
		String nome = dadosProdutoSimilar.get("nome");
		String descricao = dadosProdutoSimilar.get("descricao");
		float preco = Float.parseFloat(dadosProdutoSimilar.get("preco"));
		int quantidade = 0;
		int idProdutoOriginal = Integer.parseInt(dadosProdutoSimilar.get("produtoOriginal"));
		try {
			Produto produtoOriginal = produtos.getProduto(idProdutoOriginal);
			if (produtoOriginal instanceof ProdutoEletronico) {
				Departamento departamento = produtoOriginal.getDepartamento() ;
				int idProduto = produtos.getLista().size();
				ProdutoEletronico p = new ProdutoEletronico(idProduto, nome, descricao, preco, quantidade, departamento);
				p.setProdutoMarca((ProdutoEletronico) produtoOriginal);
				((ProdutoEletronico) produtoOriginal).cadastrarSimilar(p);
				produtos.inserir(p);
				return "Produto Cadastrado com sucesso!!!";
			}
			else {
				return "O Produto não é um eletronico!!!";
			}

		}
		catch (ProdutoNaoEncontradoException e){
			return e.getMessage();
		}
	}
}

