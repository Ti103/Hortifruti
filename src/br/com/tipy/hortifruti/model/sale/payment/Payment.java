package br.com.tipy.hortifruti.model.sale.payment;

public class Payment {

	private long id;
	private String descricao;
	private double saleTotal;

	public Payment(long id, String descricao, double saleTotal) {
		this.id = id;
		this.descricao = descricao;
		this.saleTotal = saleTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getSaleTotal() {
		return saleTotal;
	}

	public void setSaleTotal(double saleTotal) {
		this.saleTotal = saleTotal;
	}

}
