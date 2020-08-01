package br.com.tipy.hortifruti.model.sale.payment;

public class Payment {

	private long id;
	private String description;
	private double saleTotal;
	
	public Payment(long id, String description, double saleTotal) {
		this.id = id;
		this.description = description;
		this.saleTotal = saleTotal;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getSaleTotal() {
		return saleTotal;
	}
	public void setSaleTotal(double saleTotal) {
		this.saleTotal = saleTotal;
	}

}
