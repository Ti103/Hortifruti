import java.text.DecimalFormat;
import java.util.Scanner;

public class Estoque extends Hortifruti{
	public int macasDisponiveis, macasAbastecidas = 0;
	static Scanner in = new Scanner(System.in);
	
	public void gerenciarEstoque() {
		System.out.println("1 - Consultar estoque \n2 - Abastecer");
		switch(Integer.parseInt(in.nextLine())) {
		case 1:
			System.out.println("Você tem " + macasDisponiveis + " maçãs em estoque.");
			break;
		case 2:
			abastecerMacas();
		}
	}
	
	public void abastecerMacas() {
		DecimalFormat mask = new DecimalFormat("R$0.00");
		String preco = mask.format(fin.getPrecoCompra());
		System.out.println("Quantas maçãs você deseja comprar? \t Preço: " + preco);
		macasAbastecidas += Integer.parseInt(in.nextLine());
		macasDisponiveis += macasAbastecidas;
		fin.setCaixa(fin.getCaixa() - (macasAbastecidas * fin.getPrecoCompra()));
	}
}
