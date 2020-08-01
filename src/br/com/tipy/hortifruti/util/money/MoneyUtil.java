package br.com.tipy.hortifruti.util.money;

import java.text.DecimalFormat;

public class MoneyUtil {

	public static String format(double value) {
		DecimalFormat mask = new DecimalFormat("R$0.00");
		return mask.format(value);
	}
}
