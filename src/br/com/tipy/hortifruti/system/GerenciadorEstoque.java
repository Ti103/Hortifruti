package br.com.tipy.hortifruti.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.tipy.hortifruti.model.stock.ProdutoEstoque;

public class GerenciadorEstoque {
	
	private List<ProdutoEstoque> estoque;
	
	
	public GerenciadorEstoque() {
		estoque = new ArrayList<ProdutoEstoque>();
	}
	
	public void loadEstoque(List<ProdutoEstoque> list) {
		estoque = list;
	}
	
	public long getQuantidadeTotalEstoque() {
		long quantidadeTotal = 0;
		for(ProdutoEstoque pe : estoque) {
			quantidadeTotal += pe.getQtdDisponiveis();
		}
		return quantidadeTotal;
	}
	
	public long getQuantidadeTotalEstoque(String produto) {
		Optional<ProdutoEstoque> findFirst = estoque.stream().filter(p -> p.getItem().getName().equals(produto)).findFirst();
		return findFirst.isPresent() ? findFirst.get().getQtdDisponiveis() : 0;
	}

	public void setQuantidadeEstoque(String produto, long quantidade) {
		estoque.stream().filter(p -> p.getItem().getName().equals(produto)).findFirst().ifPresent(item -> item.setQtdAbastecidas(quantidade));
	}

}
