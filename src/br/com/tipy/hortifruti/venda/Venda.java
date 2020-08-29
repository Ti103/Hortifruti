package br.com.tipy.hortifruti.venda;
import java.util.List;

import br.com.tipy.hortifruti.model.sale.item.Item;;

public class Venda {
	private long cod;
	private String data;
	private String cpf;
	private List<Item> itens;
	private List<Integer> qtde;
	private double precoTotal;
	private double valorRecebido;
	private double change;
	
	public Venda(long cod, String data, String cpf) {
		this.cod = cod;
		this.data = data;
		this.cpf = cpf;
	}

	public long getCod() {
		return cod;
	}

	public void setCod(long cod) {
		this.cod = cod;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public void setQtde(List<Integer> qtde) {
		this.qtde = qtde;
	}

	public List<Item> getItens() {
		return itens;
	}

	public List<Integer> getQtde() {
		return qtde;
	}

	@Override
	public String toString() {
		return "Venda [cod=" + cod + ", data=" + data + ", cpf=" + cpf + ", itens=" + itens + ", qtde=" + qtde
				+ ", precoTotal=" + precoTotal + ", valorRecebido=" + valorRecebido + ", change=" + change + "]";
	}
	
	
	
	
}
