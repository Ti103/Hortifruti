package br.com.tipy.hortifruti.model.dayresume;

import java.util.List;

import br.com.tipy.hortifruti.model.sale.item.Item;
import br.com.tipy.hortifruti.model.sale.payment.Payment;

public class DayResume {
	
	private double valorCaixaInicial;
	private double valorCaixaFinal;
	private List<Item> itemsSold;
	private List<Payment> paymentsSold;
	
	public DayResume(double valorCaixaInicial, double valorCaixaFinal, List<Item> itemsSold, List<Payment> paymentsSold) {
		this.valorCaixaInicial = valorCaixaInicial;
		this.valorCaixaFinal = valorCaixaFinal;
		this.itemsSold = itemsSold;
		this.paymentsSold = paymentsSold;
	}

	public double getValorCaixaInicial() {
		return valorCaixaInicial;
	}

	public void setValorCaixaInicial(double valorCaixaInicial) {
		this.valorCaixaInicial = valorCaixaInicial;
	}

	public double getValorCaixaFinal() {
		return valorCaixaFinal;
	}

	public void setValorCaixaFinal(double valorCaixaFinal) {
		this.valorCaixaFinal = valorCaixaFinal;
	}

	public List<Item> getItemsSold() {
		return itemsSold;
	}

	public void setItemsSold(List<Item> itemsSold) {
		this.itemsSold = itemsSold;
	}

	public List<Payment> getPaymentsSold() {
		return paymentsSold;
	}

	public void setPaymentsSold(List<Payment> paymentsSold) {
		this.paymentsSold = paymentsSold;
	}
	
	
	public double getLiquidSale() {
		return valorCaixaFinal - valorCaixaInicial;
	}	
}
