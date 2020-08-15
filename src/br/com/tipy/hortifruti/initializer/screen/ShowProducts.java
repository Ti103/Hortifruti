package br.com.tipy.hortifruti.initializer.screen;

import java.text.Normalizer.Form;
import java.util.List;

import javax.swing.JTable;

import br.com.tipy.hortifruti.model.stock.ProdutoEstoque;
import br.com.tipy.hortifruti.util.money.MoneyUtil;

public class ShowProducts {

	
	public void show(List<ProdutoEstoque> p) {
		
		System.out.println("===================================================");
		System.out.println("Produtos:");
		int i = 1;
		for(ProdutoEstoque pp : p) {
			System.out.println(i++ + " - " + pp.getItem().getCod() + ":" + pp.getItem().getName() + " : "  + MoneyUtil.format(pp.getItem().getPrice()) + " : " + pp.getQtdDisponiveis());
		}
		System.out.println("===================================================");		
	}
}
