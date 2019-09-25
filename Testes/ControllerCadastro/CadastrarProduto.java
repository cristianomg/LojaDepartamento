package ControllerCadastro;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import Model.Entites.Departamento;
import Model.Entites.Produto;
import Model.Entites.ProdutoEletronico;

public class CadastrarProduto {
	private static List<Produto> produtos = new ArrayList<Produto>();
	private static List<Departamento> departamentos = new ArrayList<Departamento>();
	
	public String cadastrarProduto(HashMap<String, String> dadosProduto ) {
		String nome = dadosProduto.get("nome");
		String descricao = dadosProduto.get("descricao");
		float preco = Float.parseFloat(dadosProduto.get("preco"));
		int quantidade = 0;
		int idDepartamento = Integer.parseInt(dadosProduto.get("idDepartamento"));
		try {
			Departamento departamento = departamentos.get(idDepartamento);
			if (departamento.getNome().equals("Eletronico")) {
				int idProduto = produtos.size();
				ProdutoEletronico p = new ProdutoEletronico(idProduto, nome, descricao, preco, quantidade, departamento);
				produtos.add(p);
				return "Produto Cadastrado com sucesso!!!";
			}
			else {
				int idProduto = produtos.size();
				Produto p = new Produto(idProduto, nome, descricao, preco, quantidade, departamento);
				produtos.add(p);
				return "Produto Cadastrado com sucesso!!!";
			}
		}
		catch (Exception e){
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
			Produto produtoOriginal = produtos.get(idProdutoOriginal);
			if (produtoOriginal instanceof ProdutoEletronico) {
				Departamento departamento = produtoOriginal.getDepartamento() ;
				int idProduto = produtos.size();
				ProdutoEletronico p = new ProdutoEletronico(idProduto, nome, descricao, preco, quantidade, departamento);
				p.setProdutoMarca((ProdutoEletronico) produtoOriginal);
				((ProdutoEletronico) produtoOriginal).cadastrarSimilar(p);
				produtos.add(p);
				return "Produto Cadastrado com sucesso!!!";
			}
			else {
				return "O Produto não é um eletronico!!!";
			}

		}
		catch (Exception e){
			return e.getMessage();
		}
	}

	@Test
	public void testCreateProduto() {
		departamentos.add(new Departamento(0, "Eletronico", ""));
		String expected = "Produto Cadastrado com sucesso!!!";
		HashMap<String, String> dadosProduto = new HashMap<String, String>();
		dadosProduto.put("nome", "tv");
		dadosProduto.put("preco", "1000");
		dadosProduto.put("descricao", "");
		dadosProduto.put("idDepartamento", "0");
		String actual = cadastrarProduto(dadosProduto);
		assertEquals(expected, actual);
	}

	@Test
	public void testCreateProdutoSimilar() {
		this.testCreateProduto();
		String expected = "Produto Cadastrado com sucesso!!!";
		HashMap<String, String> dadosProdutoSimilar = new HashMap<String, String>();
		dadosProdutoSimilar.put("nome", "tv");
		dadosProdutoSimilar.put("preco", "1000");
		dadosProdutoSimilar.put("descricao", "");
		dadosProdutoSimilar.put("produtoOriginal", "0");
		String actual = cadastrarProdutoSimilar(dadosProdutoSimilar);
		assertEquals(expected, actual);
	}
}
