package br.com.tipy.hortifruti.system;
import java.io.IOException;

import com.google.zxing.WriterException;

import br.com.tipy.hortifruti.datetime.DateTime;
import br.com.tipy.hortifruti.errors.Erros;
import br.com.tipy.hortifruti.notafiscalfile.QRCode;
import br.com.tipy.hortifruti.notafiscalfile.file.Arquivo;
import br.com.tipy.hortifruti.notafiscalfile.file.DemoJFileChooser;

public class Financeiro extends Hortifruti{
	private double caixa, caixaInicio, lucroBruto, preco;
	private double precoCompra = Math.random() * 2;
	static int macasVendidas, credito, debito, dinheiro;
	
	
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
		lucroBruto = this.caixa - this.caixaInicio;
		System.out.println("Inicio do dia: " + formatPrecos(caixaInicio));
		System.out.println("Fim do dia: " +formatPrecos(caixa));
		System.out.println("Forma compradas " + estoque.macasAbastecidas + " maças por " + formatPrecos(precoCompra) + 
				" cada. Total: " + formatPrecos(estoque.macasAbastecidas * precoCompra));
		System.out.println("Foram vendidas " + macasVendidas + " maçãs por " + formatPrecos(preco) + " cada. Total: " + formatPrecos(macasVendidas * preco));
		System.out.println("Lucro: " + formatPrecos(lucroBruto));
	}
	
	
	
	public void gerarNotaFiscal(int qtde, double preco, double total, double recebido, double troco, String cpf) {
//		Scanner in = new Scanner(System.in);

		String str = "\n\n\n\n\n\n************************* NOTA FISCAL ***************\n";
		str += "*************** Compra realizada em " + Hortifruti.COMPANY_NAME + " ***************\n";
		str += "*****************************" + DateTime.getTime() + "*************************************\n";
		str += "Produto: maçãs\n";
		str += "Quantidade: " + qtde + "\n";
		str += "Preço unitário: " + formatPrecos(preco) + "\n";
		str += "Valor Total: " + formatPrecos(total) + "\n";
		str += "Valor recebido: " + formatPrecos(recebido) + "\n";
		str += "Troco: " + formatPrecos(troco) + "\n";
		str += "******************************" + "\n";
		str += "CPF: " + super.formatCPF(cpf) + "\n";
		str += "************* Obrigado e volte sempre *****************\n";

		try {
			QRCode.generateQRCodeImage(str, 350, 350, DemoJFileChooser.getFile().getAbsolutePath() + "/QRCode" + DateTime.getTime() + ".png");
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
	
	public void registrarVenda(String formaPagamento, double total, int qtde, String cpfCliente) {
		double recebido = 0;
		double troco = 0;
		if (formaPagamento.equals("3")) {
			System.out.println("Qual o valor recebido?");
			try {
				recebido = Double.parseDouble(in.nextLine());				
			}catch(NumberFormatException e) {
				Erros.erroDouble(e);
			}
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
			if(cartao(formaPagamento)) {
				gerarNotaFiscal(qtde, preco, total, total, troco, cpfCliente);
				estoque.macasDisponiveis -= qtde;
				caixa += total;
				macasVendidas += qtde;	
			}
		}
	}

	public boolean cartao(String formaPagamento) {
		System.out.println("Digite a senha: ");
		String senha = in.nextLine();
		if((formaPagamento.equals("1") && senha.length() < 4) || (formaPagamento.equals("2") && senha.length() < 6)) {
			System.out.println("Senha Incorreta");
			return false;
		}else {
			int num = (int) (Math.random()*100);
			if(num % 2 == 0) {
				System.out.println("Transação Não Autorizada");
				return false;
			}else {
				return true;
			}
		}
	}
}
