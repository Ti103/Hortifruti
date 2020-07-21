import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.text.MaskFormatter;

public class Hortifruti {
	
	static Scanner in = new Scanner(System.in);

	static final String COMPANY_NAME = "HORTIFRUTI DO TI";
	static Financeiro fin;
	static Estoque estoque;

	public static void main(String[] args) {
		Arquivo.start(getDate());
		abrirLoja();
		menu();
		fin.gerarReceita();
		System.exit(0);
	}
	
	public static String getDate() {
		LocalDate lt = LocalDate.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return lt.format(df);
	}

	// Abre a loja, inicializando tudo
	static void abrirLoja() {
		System.out.println("Quanto dinheiro você tem em caixa?");
		double caixa = Double.parseDouble(in.nextLine());
		fin = new Financeiro(caixa);
		estoque = new Estoque();
		
		System.out.println("Você tem " + estoque.macasDisponiveis + " maçãs em estoque. \n"
				+ "Voce pode comprar mais por " + formatPrecos(fin.getPrecoCompra()) + " cada");
		
		estoque.abastecerMacas();
		
		System.out.println("Por quanto você vai vender a unidade hoje?");
		fin.setPreco(Double.parseDouble(in.nextLine()));
	}

	// Menu inicial
	static void menu() {
		boolean loop = true;
		do {
			System.out.println("1 - Atender cliente \n2 - Gerenciar estoque \n0 - Sair");
			int opcao = Integer.parseInt(in.nextLine());
			switch (opcao) {
			case 1:
				atenderCliente();
				break;
			case 2:
				estoque.gerenciarEstoque();
				break;
			case 0:
				System.out.println("Saindo!");
				loop = false;
				break;
			}
		} while (loop);
	}

	// Todo o atendimento ao cliente
	static void atenderCliente() {

		if (estoque.macasDisponiveis == 0) {
			System.out.println("Sinto muito, acabaram as maçãs");
		} else {
			System.out.println("Ola, seja bem vinde. Quantas maçãs voce deseja?");
			int qtde = Integer.parseInt(in.nextLine());

			while (qtde > estoque.macasDisponiveis && estoque.macasDisponiveis > 0) {
				System.out.println(
						"Infelizmente só temos " + estoque.macasDisponiveis + " maçãs...\nGostaria de levar outra quantidade?");
				qtde = Integer.parseInt(in.nextLine());
			}

			double total = fin.getPreco() * qtde;
			System.out.println("Vai te custar apenas R$" + formatPrecos(total));

			System.out.println("Qual a forma de pagamento?\n1 - Crédito\n2 - Débito\n3 - Dinheiro");
			int formaPagamento = Integer.parseInt(in.nextLine());

			System.out.println("CPF na nota?\n1 - Sim\n2 - Não");
			int cpfNota = Integer.parseInt(in.nextLine());

			String cpfCliente = "";
			if (cpfNota == 1) {
				System.out.println("Digite o CPF: ");
				cpfCliente = in.nextLine();
			}

			fin.registrarVenda(formaPagamento, total, qtde, cpfCliente);
		}
	}

	// Formata o CPF
	static String formatCPF(String string) {
			MaskFormatter mask;
			try {
				mask = new MaskFormatter("###.###.###-##");
				mask.setValueContainsLiteralCharacters(false);
				return mask.valueToString(string);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

	static String formatPrecos(double preco) {
		DecimalFormat mask = new DecimalFormat("R$0.00");
		return mask.format(preco);
	}
}




