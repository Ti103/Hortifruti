package br.com.tipy.hortifruti.repository.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.github.javafaker.Faker;

import br.com.tipy.hortifruti.model.sale.item.Item;
import br.com.tipy.hortifruti.model.stock.ProdutoEstoque;

public class StockRepository {

	private static List<ProdutoEstoque> list;
	
	
	static {
		list = new ArrayList<ProdutoEstoque>();
		Faker f  = new Faker(new Locale("pt-BR", "BR"));
		for(int i = 0; i < 10; i++)
		list.add(new ProdutoEstoque(new Item((long)(Math.random() * 4999), f.food().fruit(), Math.random() * 100), 100, 0));
	}
	
	public List<ProdutoEstoque> findAll(){
		return list;
	}
	
	
	public static void main(String[] args) {
		List<ProdutoEstoque> findAll = new StockRepository().findAll();
		System.out.println(findAll);
	}


	public void minus(long cod, int qtde) {
		Optional<ProdutoEstoque> findFirst = list.stream().filter(i -> i.getItem().getCod() == cod).findFirst();
		findFirst.ifPresent(e -> e.setQtdDisponiveis(e.getQtdDisponiveis() - qtde));
	}
}
