package br.com.tipy.hortifruti.payments;

import br.com.tipy.hortifruti.model.sale.payment.Payment;

public class Pay {
	private Payment p;
	private double change = 0;
	
	public Pay (Payment p) {
		this.p = p;
	}
	
	public double moneyPay(double receivedValue) {
		change = receivedValue - p.getSaleTotal();
		if (change < 0) {
			System.out.println("Valor insuficiente. Pagar o resto com cartão?\n1 - Sim\n2 - Não");
		}
		return change;
	}
	public void cardPay(double receivedValue) {
//		TODO
	}
}
