package br.com.tipy.hortifruti.model.sale.item;

public class Item {
	private long cod;
	private String name;
	private double price;
	private double qtty;
	
	public Item(long cod, String name, double price) {
		this.cod = cod;
		this.name = name;
		this.price = price;
	}
	
	public long getCod() {
		return cod;
	}
	public void setCod(long cod) {
		this.cod = cod;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [cod=" + cod + ", name=" + name + ", price=" + price + "]";
	}
	
}
