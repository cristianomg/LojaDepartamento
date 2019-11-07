package Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.DepartamentoNaoEncontradoException;
import Exceptions.ProdutoNaoEncontradoException;
import Model.DAO.DepartamentoDAO;
import Model.DAO.ProdutoDAO;
import Model.Entites.Departamento;
import Model.Entites.Produto;
import Model.Entites.ProdutoEletronico;
import View.CadastrarClienteView;
import View.CadastrarDepartamentoView;
import View.CadastrarFuncionarioView;
import View.CadastrarProdutoSimilarView;
import View.CadastrarProdutoView;
import View.MenuCadastrosView;
import View.MenuPrincipalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuCadastroController implements Initializable{    
    @FXML
    private Button btnCadastrarDepartamento;

    @FXML
    private Button btnCadastrarFuncionario;

    @FXML
    private Button btnCadastrarProdutoSimilar;

    @FXML
    private Button btnCadastrarProduto;

    @FXML
    private Button btnVoltarMenu;

    @FXML
    private Button btnCadastrarCliente;
    
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
			
	public void btnCadastrarCliente_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarClienteView cadastrarCliente = new CadastrarClienteView(); 
		cadastrarCliente.start(new Stage());
	}
		
	public void btnCadastrarFuncionario_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarFuncionarioView cadastrarFuncionario = new CadastrarFuncionarioView(); 
		cadastrarFuncionario.start(new Stage());
	}
	
	public void btnCadastrarDepartamento_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarDepartamentoView cadastrarDepartamento = new CadastrarDepartamentoView(); 
		cadastrarDepartamento.start(new Stage());
	}
	
	public void btnCadastrarProduto_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarProdutoView cadastrarProduto = new CadastrarProdutoView(); 
		cadastrarProduto.start(new Stage());
	}
	
	public void btnCadastrarProdutoSimilar_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarProdutoSimilarView cadastrarProdutoSimilar = new CadastrarProdutoSimilarView(); 
		cadastrarProdutoSimilar.start(new Stage());
	}
	
	public void btnMenuPrincipal_Action() {
		MenuCadastrosView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());

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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

