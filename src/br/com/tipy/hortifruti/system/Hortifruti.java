package br.com.tipy.hortifruti.system;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.MaskFormatter;

import br.com.tipy.hortifruti.errors.Erros;
import br.com.tipy.hortifruti.initializer.screen.ShowProducts;
import br.com.tipy.hortifruti.model.dayresume.DayResume;
import br.com.tipy.hortifruti.model.sale.item.Item;
import br.com.tipy.hortifruti.model.stock.ProdutoEstoque;
import br.com.tipy.hortifruti.repository.stock.StockRepository;
import br.com.tipy.hortifruti.util.money.MoneyUtil;

public class Hortifruti {

	static Scanner in = new Scanner(System.in);

	static final String COMPANY_NAME = "HORTIFRUTI DO TI";
	static Financeiro fin;
	static ProdutoEstoque estoque;
	public static GerenciadorEstoque ge;

	// Abre a loja, inicializando tudo
	public void abrirLoja() {
		ge = new GerenciadorEstoque();
		System.out.println("Quanto dinheiro você tem em caixa?");
		String caixaStr = in.nextLine();
		double caixa = 0;

		try {
			caixa = Double.parseDouble(caixaStr);
		} catch (NumberFormatException e) {
			caixa = Erros.erroDouble(e);
		}

		fin = new Financeiro(caixa);
//		estoque = new ProdutoEstoque();
//
//		System.out.println("Você tem " + estoque.getMacasDisponiveis() + " maçãs em estoque. \n"
//				+ "Voce pode comprar mais por " + formatPrecos(fin.getPrecoCompra()) + " cada");

		System.out.println("Por quanto você vai vender a unidade hoje?");
		String precoStr = in.nextLine();

		try {
			fin.setPreco(Double.parseDouble(precoStr));
		} catch (NumberFormatException e) {
			fin.setPreco(Erros.erroDouble(e));
		}
	}

	// Menu inicial
	public void menu() {
		boolean lacoMenu = true;
		String op;
		do {
			System.out.println("1 - Atender cliente \n2 - Abastecer estoque \n3 - Consultar caixa \n0 - Sair");
			op = in.nextLine();
			switch (op) {
			case "1":
				atenderCliente();
				break;
			case "2":
				System.out.print("Produto: ");
				String produto = in.nextLine();
				System.out.print("Quantidade: ");
				long quantidade = Integer.parseInt(in.nextLine());
				ge.setQuantidadeEstoque(produto, quantidade);
//				estoque.gerenciarEstoque();
				break;
			case "3":
				System.out.println("Caixa: " + MoneyUtil.format(fin.getCaixa()));
				break;
			case "0":
				System.out.println("Saindo!");
				lacoMenu = false;
//				fin.gerarReceita();
				break;
			default:
				System.out.println("Digite uma opção válida");

			}

		} while (lacoMenu);
	}

	// Todo o atendimento ao cliente
	public void atenderCliente() {

		System.out.println("Ola, seja bem vindo");
		ShowProducts sp = new ShowProducts();
		int itemIndex;
		int qtde;
		double valorTotal = 0;
		StockRepository sr = new StockRepository();
		List<ProdutoEstoque> items = sr.findAll();
		do {
			sp.show(items);
			
			try {
				itemIndex = Integer.parseInt(in.nextLine());
			} catch (NumberFormatException e) {
				itemIndex = Erros.erroInt(e);
			}
			
			if(itemIndex == 0) {
				break;
			}
			
			Item produto = items.get(itemIndex - 1).getItem(); 
			
			System.out.println("Digite a quantidade: ");
			try {
				qtde = Integer.parseInt(in.nextLine());
			} catch (NumberFormatException e) {
				qtde = Erros.erroInt(e);
			}
			
			valorTotal += produto.getPrice() * qtde;
			sr.minus(produto.getCod(), qtde);
			
		}while(true);
		
		System.out.println("Valor total: " + MoneyUtil.format(valorTotal));
			
//
//			while (qtde > estoque.getMacasDisponiveis() && estoque.getMacasDisponiveis() > 0) {
//				System.out.println(
//						"Infelizmente só temos " + estoque.getMacasDisponiveis() + " maçãs...\nGostaria de levar outra quantidade?");
//				try {
//					qtde = Integer.parseInt(in.nextLine());
//				}catch(NumberFormatException e) {
//					qtde = Erros.erroInt(e);
//				}
//			}
//			
//			double total = fin.getPreco() * qtde;
//			System.out.println("Vai te custar apenas " + formatPrecos(total));

//		String formaPagamento = "";
//		boolean lacoPagamento = true;
//		while (lacoPagamento) {
//			System.out.println("Qual a forma de pagamento?\n1 - Crédito\n2 - Débito\n3 - Dinheiro");
//			formaPagamento = in.nextLine();
//			if ("123".contains(formaPagamento)) {
//				lacoPagamento = false;
//			} else {
//				System.out.println("Digite uma opção válida");
//			}
//		}
//
//		String cpfCliente = "";
//		boolean lacoCpf = true;
//		while (lacoCpf) {
//			lacoCpf = false;
//			System.out.println("CPF na nota?\n1 - Sim\n2 - Não");
//			String cpfNota = in.nextLine();
//
//			if (cpfNota.equals("1")) {
//				System.out.println("Digite o CPF: ");
//				cpfCliente = in.nextLine();
//			} else if (!cpfNota.equals("2")) {
//				System.out.println("Digite um valor válido!");
//				lacoCpf = true;
//			}
		}
//			fin.vender(formaPagamento, total, qtde, cpfCliente);
//	}

	// Formata o CPF
	public String formatCPF(String string) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("###.###.###-##");
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(string);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
