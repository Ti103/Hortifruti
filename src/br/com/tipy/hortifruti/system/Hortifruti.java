package br.com.tipy.hortifruti.system;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.text.MaskFormatter;

import br.com.tipy.hortifruti.datetime.DateTime;
import br.com.tipy.hortifruti.errors.Erros;
import br.com.tipy.hortifruti.initializer.screen.ShowProducts;
import br.com.tipy.hortifruti.model.sale.item.Item;
import br.com.tipy.hortifruti.model.sale.payment.Payment;
import br.com.tipy.hortifruti.model.stock.ProdutoEstoque;
import br.com.tipy.hortifruti.payments.ManagerPayment;
import br.com.tipy.hortifruti.repository.stock.StockRepository;
import br.com.tipy.hortifruti.util.money.MoneyUtil;
import br.com.tipy.hortifruti.venda.Venda;
import br.com.tipy.hortifruti.venda.management.ManagerVendas;

public class Hortifruti {

	static Scanner inNum = new Scanner(System.in);
	static Scanner inStr = new Scanner(System.in).useDelimiter("\n");

	public static final String COMPANY_NAME = "HORTIFRUTI DO TI";
	public Financeiro fin;
	public ProdutoEstoque estoque;
	public GerenciadorEstoque ge;
	public StockRepository sr = new StockRepository();
	public List<ProdutoEstoque> items = sr.findAll();
	public ManagerVendas mv;
	public long cod = 0;

	// Abre a loja, inicializando tudo
	public void abrirLoja() {
		mv = new ManagerVendas();
		System.out.println("Quanto dinheiro você tem em caixa?");
		String caixaStr = inStr.nextLine();
		double caixa = 0;

		try {
			caixa = Double.parseDouble(caixaStr);
		} catch (NumberFormatException e) {
			caixa = Erros.erroDouble(e);
		}

		fin = new Financeiro(caixa);
	}

	// Menu inicial
	public void menu() {
		boolean lacoMenu = true;
		String op;
		do {
			System.out.println("1 - Atender cliente \n2 - Abastecer estoque \n3 - Consultar caixa \n4 - Consultar vendas\n0 - Sair");
			op = inStr.nextLine();
			switch (op) {
			case "1":
				atenderCliente();
				break;
			case "2":
				System.out.print("Produto: ");
				int produtoIndex = inNum.nextInt();
				Item produto = items.get(produtoIndex - 1).getItem();
				System.out.print("Quantidade: ");
				int quantidade = inNum.nextInt();
				sr.plus(produto.getCod(), quantidade);
				break;
			case "3":
				System.out.println("Caixa: " + MoneyUtil.format(fin.getCaixa()));
				break;
			case "0":
				System.out.println("Saindo!");
				lacoMenu = false;
				break;
			default:
				System.out.println("Digite uma opção válida");

			}

		} while (lacoMenu);
	}

	// Todo o atendimento ao cliente
	public void atenderCliente() {
		String cpf = "";
		ShowProducts sp = new ShowProducts();
		int itemIndex;
		int qtde;
		double valorTotal = 0;
		double receivedValue = 0;

		System.out.println("Olá, seja bem vindo");
		
		System.out.println("CPF na nota? \n1)Sim \nNão");
		cpf = inStr.nextLine(); 
		if(cpf.equals("1")) {
			System.out.print("Digite o CPF: ");
			cpf = inStr.nextLine(); 
		}
		
		Venda venda = new Venda(cod++, DateTime.getDateTime(), cpf);
		do {
			sp.show(items);
			
			try {
				itemIndex = inNum.nextInt();
			} catch (NumberFormatException e) {
				itemIndex = Erros.erroInt(e);
			}
			
			if(itemIndex == 0) {
				break;
			}
			
			Item produto = items.get(itemIndex - 1).getItem();
			venda.getItens().add(produto);
			
			System.out.println("Digite a quantidade: ");
			try {
				qtde = inNum.nextInt();
			} catch (NumberFormatException e) {
				qtde = Erros.erroInt(e);
			}
			
			venda.getQtde().add(qtde);
			
			valorTotal += produto.getPrice() * qtde;
			sr.minus(produto.getCod(), qtde);
			
		}while(true);
		
		
		long id;
		String descricao;
		if(valorTotal != 0) {
			
			do {
				System.out.println("Forma de pagamento:\n1 - Dinheiro\n2 - Cartão");
				
				try {
					id = inNum.nextLong();
				} catch (NumberFormatException e) {
					id = Erros.erroLong(e);
				}
				if(id == 1) {
					descricao = "Dinheiro";
					break;
				}else if(id == 2 ) {
					descricao = "Cartão";
					break;
				}else {
					System.out.println("Opção inválida");
				}
			} while (true);
			
			System.out.println("Valor total: " + MoneyUtil.format(valorTotal));
			System.out.print("Valor pago: ");
			
			try {
				receivedValue = inNum.nextLong();
			} catch(NumberFormatException e) {
				receivedValue = Erros.erroLong(e);
			}
			
			ManagerPayment mp = new ManagerPayment(new Payment(id, descricao, valorTotal));
						
			switch(Integer.parseInt(mp.getP().getId().toString())) {
			case 1:
				mp.moneyPay(receivedValue, valorTotal);
				break;
			case 2:
				mp.cardPay(receivedValue);
			}
		}
		mv.registrarVenda(venda, valorTotal, receivedValue, receivedValue - valorTotal);
	}
		

	// Formata o CPF
	public static String formatCPF(String string) {
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
