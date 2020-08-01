package br.com.tipy.hortifruti.initializer;

import br.com.tipy.hortifruti.datetime.DateTime;
import br.com.tipy.hortifruti.notafiscalfile.file.Arquivo;
import br.com.tipy.hortifruti.system.Hortifruti;

public class Initializer {
	public static void main(String[] args) {
		Hortifruti h = new Hortifruti();
		Arquivo.start(DateTime.getDate());
		h.abrirLoja();
		h.menu();
		
		System.exit(0);
	}
}
