package br.com.tipy.hortifruti.report.total.dayresume;

import br.com.tipy.hortifruti.model.dayresume.DayResume;
import br.com.tipy.hortifruti.util.money.MoneyUtil;

public class DayResumeReport {

	private DayResume model; 
	
	public DayResumeReport(DayResume model) {
		this.model = model;
	}
	
	public void print() {
		System.out.println("Inicio do dia: " + MoneyUtil.format(model.getValorCaixaInicial()));
		System.out.println("Fim do dia: " + MoneyUtil.format(model.getValorCaixaFinal()));
		System.out.println("Total vendido: " + MoneyUtil.format(model.getLiquidSale()));
		System.out.println("Totalizadores por pagamento:");
		model.getPaymentsSold().forEach(p ->{
			System.out.println(p.getId() + "-" + p.getDescription() + ": " + MoneyUtil.format(p.getSaleTotal()));
		});
	}
}

