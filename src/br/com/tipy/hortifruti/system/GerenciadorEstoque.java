package br.com.tipy.hortifruti.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.tipy.hortifruti.model.ProdutoEstoque;

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
		Optional<ProdutoEstoque> findFirst = estoque.stream().filter(p -> p.getDescricaoProduto().equals(produto)).findFirst();
		return findFirst.isPresent() ? findFirst.get().getQtdDisponiveis() : 0;
	}

	public void setQuantidadeEstoque(String produto, long quantidade) {
		estoque.stream().filter(p -> p.getDescricaoProduto().equals(produto)).findFirst().ifPresent(item -> item.setQtdAbastecidas(quantidade));
	}
	
//	public void abastecerMacas() {
//		String preco = formatPrecos(fin.getPrecoCompra());
//		System.out.println("Quantas maçãs você deseja comprar? \t Preço: " + preco);
//		try {
//			setMacasAbastecidas(getMacasAbastecidas() + Integer.parseInt(in.nextLine()));			
//		}catch(NumberFormatException e) {
//			Erros.erroInt(e);
//		}
//		setMacasDisponiveis(getMacasDisponiveis() + getMacasAbastecidas());
//		fin.setCaixa(fin.getCaixa() - (getMacasAbastecidas() * fin.getPrecoCompra())); 
//	}
}
