package br.com.tipy.hortifruti.venda.management;

import java.util.ArrayList;
import java.util.List;

import br.com.tipy.hortifruti.venda.Venda;

public class ManagerVendas {
	private List<Venda> vendas = new ArrayList();

	public List<Venda> getVendas() {
		return vendas;
	}
	
	public void registrarVenda(Venda venda, double valorTotal, double receivedValue, double change) {
		venda.setPrecoTotal(valorTotal);
		venda.setValorRecebido(receivedValue);
		venda.setChange(change);
		vendas.add(venda);
	}
	
	public void consultarVenda(int cod) {
		vendas.stream().filter(venda -> venda.getCod() == cod).findFirst().ifPresent(venda -> System.out.println(venda.toString()));
	}
	
}
