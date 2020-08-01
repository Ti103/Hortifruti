package br.com.tipy.hortifruti.report.total.dayresume;

import java.util.ArrayList;
import java.util.List;

import br.com.tipy.hortifruti.model.dayresume.DayResume;
import br.com.tipy.hortifruti.model.sale.item.Item;
import br.com.tipy.hortifruti.model.sale.payment.Payment;

public class DayResumeReportTest {
	
	public static void main(String[] args) {
		
		List<Item> listItem = new ArrayList<Item>();
		List<Payment> listPayment = new ArrayList<Payment>();
		listPayment.add(new Payment(1, "Dinheiro", 345));
		DayResume dr = new DayResume(100, 200, listItem, listPayment);
		DayResumeReport report = new DayResumeReport(dr);
		report.print();
	}
	
}
