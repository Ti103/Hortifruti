package br.com.tipy.hortifruti.model.stock;

import br.com.tipy.hortifruti.model.sale.item.Item;

public class ProdutoEstoque {
	
//	private String descricaoProduto;
	private Item item;
	private long qtdDisponiveis;
	private long qtdAbastecidas = 0;

	public ProdutoEstoque(Item item, /*String descricaoProduto,*/ long qtdDisponiveis, long qtdAbastecidas) {
//		this.descricaoProduto = descricaoProduto;
		this.item = item;
		this.qtdDisponiveis = qtdDisponiveis;
		this.qtdAbastecidas = qtdAbastecidas;
	}

//	public String getDescricaoProduto() {
//		return descricaoProduto;
//	}

//	public void setDescricaoProduto(String descricaoProduto) {
//		this.descricaoProduto = descricaoProduto;
//	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	public long getQtdDisponiveis() {
		return qtdDisponiveis;
	}

	public void setQtdDisponiveis(int qtdDisponiveis) {
		this.qtdDisponiveis = qtdDisponiveis;
	}

	public void setQtdAbastecidas(long qtdAbastecidas) {
		this.qtdAbastecidas += qtdAbastecidas;
		this.qtdDisponiveis += qtdAbastecidas;
	}
	
	public long getQtdAbastecidas() {
		return qtdAbastecidas;
	}

}
