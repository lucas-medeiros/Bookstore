package Objetos;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Pedido {

	private GregorianCalendar data;
	private ArrayList<Livro> livros;
	private Cliente cliente;
	
	public Pedido(GregorianCalendar data, ArrayList<Livro> livros, Cliente cliente) {
		super();
		this.data = data;
		this.livros = livros;
		this.cliente = cliente;
	}
	
	public GregorianCalendar getData() {
		return data;
	}
	
	public void setData(GregorianCalendar data) {
		this.data = data;
	}
	
	public ArrayList<Livro> getLivros() {
		return livros;
	}
	
	public void setLivros(ArrayList<Livro> livros) {
		this.livros = livros;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}