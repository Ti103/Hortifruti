package br.com.tipy.hortifruti.system;

import java.util.ArrayList;
import java.util.List;

import br.com.tipy.hortifruti.model.sale.item.Item;
import br.com.tipy.hortifruti.model.stock.ProdutoEstoque;

public class GerenciadorEstoqueTeste {
	
	public static void main(String[] args) {
		int i = 4;
		GerenciadorEstoque g = new GerenciadorEstoque();
		g.loadEstoque(geradorProdutoEstoque(i));
		System.out.println(g.getQuantidadeTotalEstoque());
		showQuantidadeEstoquePorProduto(i, g);
		alterarQuantidadeEstoquePorProduto(i, g);
		showQuantidadeEstoquePorProduto(i, g);
		System.out.println(g.getQuantidadeTotalEstoque());
	}


	private static void showQuantidadeEstoquePorProduto(int i, GerenciadorEstoque g) {
		for(int j = 0; j < i; j++) {
			System.out.println("Produto" + j  + " quantidade " + g.getQuantidadeTotalEstoque("Produto" + j));
		}
	}
	
	private static void alterarQuantidadeEstoquePorProduto(int i, GerenciadorEstoque g) {
		System.out.println("Mexendo no estoque");
		for(int j = 0; j < i; j++) {
			g.setQuantidadeEstoque("Produto" + j, (long)(Math.random() * 674));
		}
		System.out.println("Finalizado estoque");
	}
	
	
	static List<ProdutoEstoque> geradorProdutoEstoque(int i) {
		List<ProdutoEstoque> l = new ArrayList<>();
		while (i-- > 0) {
			l.add(new ProdutoEstoque(new Item((long)(Math.random() * 674), "Produto" + 1, (Math.random() * 674)), 100, 0));
		}
		return l;
	}
}
