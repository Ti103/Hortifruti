package br.com.tipy.hortifruti.model.sale.payment;

import br.com.tipy.hortifruti.payments.methods.PaymentMethods;

public class Payment {

	private PaymentMethods method;
	private double saleTotal;
	
	public Payment(PaymentMethods method, double saleTotal) {
		this.method = method;
		this.saleTotal = saleTotal;
	}
	
	public PaymentMethods getPm() {
		return method;
	}

	public void setPm(PaymentMethods method) {
		this.method = method;
	}

	public double getSaleTotal() {
		return saleTotal;
	}
	public void setSaleTotal(double saleTotal) {
		this.saleTotal = saleTotal;
	}

}
