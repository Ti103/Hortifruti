package br.com.tipy.hortifruti.system;
import java.io.IOException;


import br.com.tipy.hortifruti.datetime.DateTime;
import br.com.tipy.hortifruti.errors.Erros;
import br.com.tipy.hortifruti.model.dayresume.DayResume;
import br.com.tipy.hortifruti.notafiscalfile.QRCode;
import br.com.tipy.hortifruti.notafiscalfile.file.Arquivo;
import br.com.tipy.hortifruti.notafiscalfile.file.DemoJFileChooser;
import br.com.tipy.hortifruti.util.money.MoneyUtil;

public class Financeiro extends Hortifruti{
	
	private double caixa, caixaInicio, lucroBruto, preco;
	private double precoCompra = Math.random() * 2;
	private int macasVendidas = 0, credito = 0, debito = 0, dinheiro = 0;
	
	
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
	
	
	public void gerarNotaFiscal(int qtde, double preco, double total, double recebido, double troco, String cpf) {
//		Scanner in = new Scanner(System.in);

		String str = "\n\n\n\n\n\n************************* NOTA FISCAL ***************\n";
		str += "*************** Compra realizada em " + Hortifruti.COMPANY_NAME + " ***************\n";
		str += "*****************************" + DateTime.getTime() + "*************************************\n";
		str += "Produto: maçãs\n";
		str += "Quantidade: " + qtde + "\n";
		str += "Preço unitário: " + MoneyUtil.format(preco) + "\n";
		str += "Valor Total: " + MoneyUtil.format(total) + "\n";
		str += "Valor recebido: " + MoneyUtil.format(recebido) + "\n";
		str += "Troco: " + MoneyUtil.format(troco) + "\n";
		str += "******************************" + "\n";
		str += "CPF: " + super.formatCPF(cpf) + "\n";
		str += "************* Obrigado e volte sempre *****************\n";

		
		
		try {
			Arquivo.write(str);
		}catch(Exception e) {
			System.out.println("Não foi possivel imprimir a nota. ERR0: " + e.getMessage());
		}

		System.out.println(str + "\n\n");
		
	}
	
	public void vender(String formaPagamento, double total, int qtde, String cpfCliente) {
//		double recebido = 0;
		double troco = 0;
		
		switch(formaPagamento) {
		case "1":
			System.out.println("Insira a senha");
			in.nextLine();
			registrarVenda(qtde, total);
			this.credito++;
			gerarNotaFiscal(qtde, preco, total, total, troco, cpfCliente);
			break;
		case "2":
			System.out.println("Insira a senha");
			in.nextLine();
			registrarVenda(qtde, total);
			this.debito++;
			gerarNotaFiscal(qtde, preco, total, total, troco, cpfCliente);
			break;
		case "3":
			venderDinheiro(qtde, total, cpfCliente);
		}
	}
	
	public void registrarVenda(int qtde, double total) {
//		estoque.setMacasDisponiveis(estoque.getMacasDisponiveis() - qtde);
		caixa += total;
		macasVendidas += qtde;
	}
	
	public void venderDinheiro(int qtde, double total, String cpf) {
		System.out.println("Qual o valor recebido?");
		double recebido = 0;
		double troco = 0;
		try {
			recebido = Double.parseDouble(in.nextLine());				
		}catch(NumberFormatException e) {
			Erros.erroDouble(e);
		}
		if (recebido >= total) {
			troco = recebido - total;
			registrarVenda(qtde, total);
			gerarNotaFiscal(qtde, recebido, total, recebido, troco, cpf);
		}else {
			System.out.println("Valor Insuficiente!");
		}
	}
}
