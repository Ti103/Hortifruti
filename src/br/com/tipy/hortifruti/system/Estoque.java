package br.com.tipy.hortifruti.system;

import br.com.tipy.hortifruti.errors.Erros;

public class Estoque extends Hortifruti{
	private int macasDisponiveis, macasAbastecidas = 0;

	public int getMacasDisponiveis() {
		return macasDisponiveis;
	}

	public void setMacasDisponiveis(int macasDisponiveis) {
		this.macasDisponiveis = macasDisponiveis;
	}

	public int getMacasAbastecidas() {
		return macasAbastecidas;
	}

	public void setMacasAbastecidas(int macasAbastecidas) {
		this.macasAbastecidas = macasAbastecidas;
	}

	public void gerenciarEstoque() {
		System.out.println("1 - Consultar estoque \n2 - Abastecer");

		switch(in.nextLine()) {
		case "1":
			System.out.println("Você tem " + getMacasDisponiveis() + " maçãs em estoque.");
			break;
		case "2":
			abastecerMacas();
			break;
		default:
			System.out.println("Digite uma opção válida!");
		}

	}
	
	public void abastecerMacas() {
		String preco = formatPrecos(fin.getPrecoCompra());
		System.out.println("Quantas maçãs você deseja comprar? \t Preço: " + preco);
		try {
			setMacasAbastecidas(getMacasAbastecidas() + Integer.parseInt(in.nextLine()));			
		}catch(NumberFormatException e) {
			Erros.erroInt(e);
		}
		setMacasDisponiveis(getMacasDisponiveis() + getMacasAbastecidas());
		fin.setCaixa(fin.getCaixa() - (getMacasAbastecidas() * fin.getPrecoCompra())); 
	}
}
