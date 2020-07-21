import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.google.zxing.WriterException;

public class Financeiro extends Hortifruti{
	private double caixa, caixaInicio, lucroBruto, preco;
	private double precoCompra = Math.random() * 2;
	static int macasVendidas;
	static Scanner in = new Scanner(System.in);
	
	public double getCaixa() {
		return caixa;
	}

	public void setCaixa(double caixa) {
		this.caixa = caixa;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public double getCaixaInicio() {
		return caixaInicio;
	}

	public Financeiro(double caixa) {
		this.caixa = caixa;
		this.caixaInicio = caixa;
	}
	
	public void gerarReceita() {
		DecimalFormat mask = new DecimalFormat("0.00");
		lucroBruto = this.caixa - this.caixaInicio;
		System.out.println("Inicio do dia: " + mask.format(caixaInicio));
		System.out.println("Fim do dia: " + mask.format(caixa));
		System.out.println("Lucro: " + mask.format(lucroBruto));
		System.out.println("Foram vendidas " + macasVendidas + " maçãs a " +mask.format(preco));
	}
	
	public String getTime() {
		LocalTime lt = LocalTime.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:SS");
		return lt.format(df);
	}
	
	public void gerarNotaFiscal(int qtde, double preco, double total, double recebido, double troco, String cpf) {
//		Scanner in = new Scanner(System.in);

		String str = "\n\n\n\n\n\n************************* NOTA FISCAL ***************\n";
		str += "*************** Compra realizada em " + Hortifruti.COMPANY_NAME + " ***************\n";
		str += "*****************************" + getTime() + "*************************************\n";
		str += "Produto: maçãs\n";
		str += "Quantidade: " + qtde + "\n";
		str += "Preço unitário: " + formatPrecos(preco) + "\n";
		str += "Valor Total: " + formatPrecos(total) + "\n";
		str += "Valor recebido: " + formatPrecos(recebido) + "\n";
		str += "Troco: R$" + formatPrecos(troco) + "\n";
		str += "******************************" + "\n";
		str += "CPF: " + Hortifruti.formatCPF(cpf) + "\n";
		str += "************* Obrigado e volte sempre *****************\n";

		try {
			QRCode.generateQRCodeImage(str, 350, 350, "./QRCode.png");
		}catch (WriterException e) {
			System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		}
		
		try {
			Arquivo.write(str);
		}catch(Exception e) {
			System.out.println("Não foi possivel imprimir a nota. ERR0: " + e.getMessage());
		}

		System.out.println(str + "\n\n");
		
	}
	
	public void registrarVenda(int formaPagamento, double total, int qtde, String cpfCliente) {
		double recebido = 0;
		double troco = 0;
		if (formaPagamento == 3) {
			System.out.println("Qual o valor recebido?");
			recebido = Double.parseDouble(in.nextLine());
			if (recebido >= total) {
				troco = recebido - total;
				gerarNotaFiscal(qtde, preco, total, recebido, troco, cpfCliente);
				estoque.macasDisponiveis -= qtde;
				caixa += total;
				macasVendidas += qtde;
			}else {
				System.out.println("Valor Insuficiente!");
			}
		}else {
			gerarNotaFiscal(qtde, preco, total, total, troco, cpfCliente);
			estoque.macasDisponiveis -= qtde;
			caixa += total;
			macasVendidas += qtde;
		}
	}
}
