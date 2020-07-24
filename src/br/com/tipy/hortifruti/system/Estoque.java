package br.com.tipy.hortifruti.system;

import br.com.tipy.hortifruti.errors.Erros;

public class Estoque extends Hortifruti{
	public int macasDisponiveis, macasAbastecidas = 0;

	public void gerenciarEstoque() {
		System.out.println("1 - Consultar estoque \n2 - Abastecer");

		switch(in.nextLine()) {
		case "1":
			System.out.println("Você tem " + macasDisponiveis + " maçãs em estoque.");
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
			macasAbastecidas += Integer.parseInt(in.nextLine());			
		}catch(NumberFormatException e) {
			Erros.erroInt(e);
		}
		macasDisponiveis += macasAbastecidas;
		fin.setCaixa(fin.getCaixa() - (macasAbastecidas * fin.getPrecoCompra())); 
	}
}
