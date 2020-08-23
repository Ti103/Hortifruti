package br.com.tipy.hortifruti.payments;

import java.util.Scanner;

import br.com.tipy.hortifruti.errors.Erros;
import br.com.tipy.hortifruti.model.sale.payment.Payment;

public class ManagerPayment {
	private Payment p;
	private double change = 0;
	static Scanner in = new Scanner(System.in);

	public ManagerPayment(Payment p) {
		this.p = p;
	}

	public Payment getP() {
		return p;
	}

	public void setP(Payment p) {
		this.p = p;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double moneyPay(double receivedValue, double totalValue) {
		change = receivedValue - p.getSaleTotal();
		if (change < 0) {
			System.out.println("Valor insuficiente. Pagar o resto com cartão?\n1 - Sim\n2 - Não");
			switch (Integer.parseInt(in.nextLine())) {
			case 1:
				cardPay(change * (-1));
				break;
			case 2:
				System.out.println("Cancelando compra");
			}
		} else {
			System.out.println("Valor recebido: " + receivedValue + "/n" + "Troco: " + change);
		}
		return change;
//		
//			registrarVenda(qtde, total);
//			gerarNotaFiscal(qtde, recebido, total, recebido, troco, cpf);
		
	}

	public void cardPay(double receivedValue) {
		System.out.println("Transação autorizada!");
	}
}
