package br.com.tipy.hortifruti.model;

public class ProdutoEstoque {
	
	private String descricaoProduto;
	private long qtdDisponiveis;
	private long qtdAbastecidas = 0;

	public ProdutoEstoque(String descricaoProduto, long qtdDisponiveis, long qtdAbastecidas) {
		this.descricaoProduto = descricaoProduto;
		this.qtdDisponiveis = qtdDisponiveis;
		this.qtdAbastecidas = qtdAbastecidas;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public long getQtdDisponiveis() {
		return qtdDisponiveis;
	}

	public void setQtdDisponiveis(int qtdDisponiveis) {
		this.qtdDisponiveis = qtdDisponiveis;
	}

	public long getQtdAbastecidas() {
		return qtdAbastecidas;
	}

	public void setQtdAbastecidas(long qtdAbastecidas) {
		this.qtdAbastecidas += qtdAbastecidas;
		this.qtdDisponiveis += qtdAbastecidas;
	}
}
