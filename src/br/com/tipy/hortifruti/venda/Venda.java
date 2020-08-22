package br.com.tipy.hortifruti.venda;
import java.util.List;

import br.com.tipy.hortifruti.model.sale.item.Item;;

public class Venda {
	private long cod;
	private String data;
	private String cpf;
	private List<Item> itens;
	
	
	public Venda(long cod, String data, String cpf, Item itens) {
		this.cod = cod;
		this.data = data;
		this.cpf = cpf;
		this.itens.add(itens);
	}
	
	
}
