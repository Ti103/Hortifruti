package br.com.tipy.hortifruti.errors;
import java.util.Scanner;

public class Erros {
	static Scanner in = new Scanner(System.in); 
	
	public static int erroInt(NumberFormatException e) {
		System.out.println("Digite um valor numérico válido");
		
		try {
			return in.nextInt();
		} catch (NumberFormatException f) {
			return erroInt(f);
		}
	}
	
	public static double erroDouble(NumberFormatException e) {
		System.out.println("Digite um valor numérico válido");

		try {
			return in.nextDouble();
		} catch (NumberFormatException f) {
			return erroDouble(f);
		}
	}
	
	public static long erroLong(NumberFormatException e) {
		System.out.println("Digite um valor numérico válido");

		try {
			return in.nextLong();
		} catch (NumberFormatException f) {
			return erroLong(f);
		}
	}
}
