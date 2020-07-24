package br.com.tipy.hortifruti.system;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.text.MaskFormatter;

import br.com.tipy.hortifruti.datetime.DateTime;
import br.com.tipy.hortifruti.errors.Erros;
import br.com.tipy.hortifruti.notafiscalfile.file.Arquivo;

public class Hortifruti {
	
	static Scanner in = new Scanner(System.in);

	static final String COMPANY_NAME = "HORTIFRUTI DO TI";
	static Financeiro fin;
	static Estoque estoque;

//	excluir
	public void main(String[] args) {
		Arquivo.start(DateTime.getDate());
		abrirLoja();
		menu();
		fin.gerarReceita();
		System.exit(0);
	}

	// Abre a loja, inicializando tudo
	public void abrirLoja() {
		System.out.println("Quanto dinheiro você tem em caixa?");
		String caixaStr = in.nextLine();
		double caixa = 0;
		
		try{
			caixa = Double.parseDouble(caixaStr);
		}catch(NumberFormatException e){
			caixa = Erros.erroDouble(e);
		}

		fin = new Financeiro(caixa);
		estoque = new Estoque();

		System.out.println("Você tem " + estoque.macasDisponiveis + " maçãs em estoque. \n"
				+ "Voce pode comprar mais por " + formatPrecos(fin.getPrecoCompra()) + " cada");

		estoque.abastecerMacas();

		System.out.println("Por quanto você vai vender a unidade hoje?");
		String precoStr = in.nextLine();

		try {
			fin.setPreco(Double.parseDouble(precoStr));
		}catch(NumberFormatException e) {
			fin.setPreco(Erros.erroDouble(e));
		}
	}

	// Menu inicial
	public void menu() {
		boolean lacoMenu = true;
		String op;
		do {
			System.out.println("1 - Atender cliente \n2 - Gerenciar estoque \n3 - Consultar caixa \n0 - Sair");
			op = in.nextLine();
			switch (op) {
			case "1":
				atenderCliente();
				break;
			case "2":
				estoque.gerenciarEstoque();
				break;
			case "3":
				System.out.println("Caixa: " + formatPrecos(fin.getCaixa()));
				break;
			case "0":
				System.out.println("Saindo!");
				lacoMenu = false;
				fin.gerarReceita();
				break;
			default:
				System.out.println("Digite uma opção válida");
				
			}

		} while (lacoMenu);
	}

	// Todo o atendimento ao cliente
	public void atenderCliente() {

		if (estoque.macasDisponiveis == 0) {
			System.out.println("Sinto muito, acabaram as maçãs");
		} else {
			System.out.println("Ola, seja bem vinde. Quantas maçãs voce deseja?");
			int qtde = 0;
			try {				
				qtde = Integer.parseInt(in.nextLine());
			}catch(NumberFormatException e) {
				qtde = Erros.erroInt(e);
			}

			while (qtde > estoque.macasDisponiveis && estoque.macasDisponiveis > 0) {
				System.out.println(
						"Infelizmente só temos " + estoque.macasDisponiveis + " maçãs...\nGostaria de levar outra quantidade?");
				try {
					qtde = Integer.parseInt(in.nextLine());
				}catch(NumberFormatException e) {
					qtde = Erros.erroInt(e);
				}
			}
			
			double total = fin.getPreco() * qtde;
			System.out.println("Vai te custar apenas " + formatPrecos(total));

			String formaPagamento = "";
			boolean lacoPagamento = true;
			while(lacoPagamento) {
				System.out.println("Qual a forma de pagamento?\n1 - Crédito\n2 - Débito\n3 - Dinheiro");
				formaPagamento = in.nextLine();
				if("123".contains(formaPagamento)) {
					lacoPagamento = false;
				}else {
					System.out.println("Digite uma opção válida");
				}
			}
			
			String cpfCliente = "";
			boolean lacoCpf = true;
			while(lacoCpf) {
				lacoCpf = false;
				System.out.println("CPF na nota?\n1 - Sim\n2 - Não");
				String cpfNota = in.nextLine();
				
				if (cpfNota.equals("1")) {
					System.out.println("Digite o CPF: ");
					cpfCliente = in.nextLine();
				}else if(!cpfNota.equals("2")) {
					System.out.println("Digite um valor válido!");
					lacoCpf = true;
				}
			}
			fin.registrarVenda(formaPagamento, total, qtde, cpfCliente);
		}
	}

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

	public String formatPrecos(double preco) {
		DecimalFormat mask = new DecimalFormat("R$0.00");
		return mask.format(preco);
	}
}




