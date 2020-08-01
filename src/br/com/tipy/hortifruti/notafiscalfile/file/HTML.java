package br.com.tipy.hortifruti.notafiscalfile.file;

public class HTML {
	
	public static void escreverHtml(String campo) {
		String str = "<html><head></head><body>" + campo + "</body></html>";
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		escreverHtml("Ola,br><br>Meu nome é Tiago");
	}
}
